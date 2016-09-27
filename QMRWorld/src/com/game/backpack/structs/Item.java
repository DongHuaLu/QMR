package com.game.backpack.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.ItemAppendManager;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.object.GameObject;


public abstract class Item extends GameObject {

	private static final Logger logger = Logger.getLogger(Item.class);
	
	private static final long serialVersionUID = 697107364045687657L;

	private int itemModelId;
	
	private int num;
	
	private int packageId;
	
	private int gridId;
	//是否绑定
	private boolean bind;
	//失效时间
	private int losttime;
	//道具状态
	private transient byte status ;
	//是否交易状态
	protected transient boolean trade;
	
	protected transient boolean lost;
	//临时数据
	private HashMap<String, String> parameters = new HashMap<String, String>();
	
	protected transient GoodsInfoRes chatres=new GoodsInfoRes();
	
	public int getItemModelId() {
		return itemModelId;
	}

	public void setItemModelId(int itemModelId) {
		this.itemModelId = itemModelId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getGridId() {
		return gridId;
	}

	public void setGridId(int gridId) {
		this.gridId = gridId;
	}
	
	public boolean isBind() {
		return bind;
	}

	public void setBind(boolean bind) {
		this.bind = bind;
	}

	public int getLosttime() {
		return losttime;
	}

	public void setLosttime(int losttime) {
		this.losttime = losttime;
	}

	
	public Item clone() throws CloneNotSupportedException{
		return (Item)super.clone();
	}

	/**
	 * 获取物品信息
	 * @param item 物品
	 * @return
	 */
	public ItemInfo buildItemInfo(){
		ItemInfo info = new ItemInfo();
		info.setItemId(getId());
		info.setItemModelId(getItemModelId());
		info.setNum(getNum());
		info.setGridId(getGridId());
		info.setIsbind((byte) (isBind()?1:0));
		if(this instanceof Equip || this instanceof HorseEquip){
			Equip equip=(Equip) this;
			if(equip.getAttributes()!=null){
				info.setAttributs((byte) equip.getAttributes().size());
				for (Attribute attribute : equip.getAttributes()) {
					info.getGoodAttributes().add(attribute.buildGoodsAttribute());		
				}
			}else{
				info.setAttributs((byte) 0);
			}
			info.setIntensify((byte) equip.getGradeNum());
			info.setIsFullAppend((byte) (equip.isFullAppend()?1:0));
		}
		info.setLostTime(getLosttime());
		return info;
	}
	
	// -1铜币，-2元宝，-3经验，-4真气  -5绑定元宝 -6战魂值 -7军功
	
	public static Item createMoney(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-1);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createGold(int num,boolean isbind){
		Item item=new CommonGoods();
		item.setBind(isbind);
		item.setItemModelId(-2);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createBindGold(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-5);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createZhenQi(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-3);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createExp(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-4);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createFightSpirit(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-6);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static Item createRank(int num){
		Item item=new CommonGoods();
		item.setItemModelId(-7);
		item.setNum(num);
		item.setId(Config.getId());
		return item;
	}
	
	public static List<Item> createItems(int itemModelId, int num, boolean bind, long losttime, int gradeNum, String append) {
		List<Item> createItems = createItems(itemModelId, num, bind, losttime);
		if(createItems==null||createItems.size()<=0){
			return createItems;
		}
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
		if(q_itemBean.getQ_type()==ItemTypeConst.EQUIP||q_itemBean.getQ_type()==ItemTypeConst.HORSEEQUIP){
			for (Item item : createItems) {
				Equip equip = (Equip) item;
				equip.setGradeNum(gradeNum);
				ItemAppendManager.getInstance().buildAppend(equip, append);
			}	
		}
		return createItems;
	}
	
	public static List<Item> createItems(int itemModelId,int num,boolean bind,long losttime,int grade,int appendcount){
		List<Item> createItems = createItems(itemModelId, num, bind, losttime);
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
		if(q_itemBean.getQ_type()==ItemTypeConst.EQUIP||q_itemBean.getQ_type()==ItemTypeConst.HORSEEQUIP){
			for (Item item : createItems) {
				Equip equip = (Equip) item;
				equip.setGradeNum(grade);
				ItemAppendManager.getInstance().buildAppend(equip, appendcount);
			}	
		}
		return createItems;
	}
	
	public static List<Item> createItems(int itemModelId, int num, boolean bind, long losttime){
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
		List<Item> list=new ArrayList<Item>();
		if(q_itemBean==null){
			logger.error("物品模型ID"+itemModelId+"找不到",new Exception("通知策划检查数据物品模型ID"+itemModelId+"找不到"));
			return list;
		}
		int count=num;
		while (count>0) {
			Item item=null;
			int itemnum=0;
			if(count>q_itemBean.getQ_max()){
				itemnum=q_itemBean.getQ_max();
			}else{
				itemnum=count;
			}
			
			count-=itemnum;
			switch (q_itemBean.getQ_type()) {
			case ItemTypeConst.COPPER:
				list.add(createMoney(num));
				return list;
			case ItemTypeConst.EQUIP:
				item = new Equip();
				break;
			case ItemTypeConst.MEDICINES:
				item = new Medicines();
				break;
			case ItemTypeConst.TRANSSCROLL:
				item = new ScrollTrans();
				break;
			case ItemTypeConst.TASKSCROLL:
				item = new ScrollTask();
				break;
			case ItemTypeConst.SKILLBOOK://技能书
				item = new Book();
				break;
			case ItemTypeConst.GIFT:
				item = new Gift();				
				break;
			case ItemTypeConst.SCRIPT:
				item = new ScriptItem();				
				break;
			case ItemTypeConst.PANEL:
				item = new PanelItem();				
				break;
			case ItemTypeConst.HORSEEQUIP:
				item=new HorseEquip();
				break;
			case ItemTypeConst.BUFF:
				item=new BuffGoods();
				break;
			case ItemTypeConst.ATTRIBUTE:
				item=new AttributeItem();
				break;
			case 10:				//普通物品
			case 11:				//任务物品
			case 12:				//可合成物品
			case 3:					//宝石
			case 14:				//玫瑰
				item =new CommonGoods();
				break;
			default:
				logger.error("找不到定义的物品类型"+q_itemBean.getQ_type(),new Exception());
				item=new CommonGoods();
				break;
			}
			item.setBind(bind);	
			item.setItemModelId(itemModelId);
			item.setNum(itemnum);
			item.setId(Config.getId());
			if(losttime!=0){
				item.setLosttime((int) (losttime/1000));	
			}
			list.add(item);
		}
		return list;
	}
	
	/**
	 * 是否己过时
	 * @return
	 */
	public boolean isLost(){
		return losttime>0?System.currentTimeMillis()>losttime*1000l:false;
	}

	public boolean isTrade(){
		return status==1;
	}
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
