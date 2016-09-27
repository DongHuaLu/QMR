package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_conquer
	 */
	public class Q_task_conquer extends Bean{
	
		//讨伐任务编号
		private var _q_id: int;
		
		//玩家等级区间min
		private var _q_mingrade: int;
		
		//玩家等级区间max
		private var _q_maxgrade: int;
		
		//卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
		private var _q_scroll_type: int;
		
		//美术图片资源编号
		private var _q_image: int;
		
		//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		private var _q_monstercount: String;
		
		//任务接取时需检测达成某成就序列（成就编号;成就编号）
		private var _q_accept_needAchieve: String;
		
		//接取任务时发送的屏幕提示信息文字
		private var _q_accept_prompt: String;
		
		//任务奖励达成某成就序列（成就编号;成就编号）
		private var _q_rewards_achieve: String;
		
		//任务奖励经验
		private var _q_rewards_exp: int;
		
		//任务奖励铜钱
		private var _q_rewards_coin: int;
		
		//任务奖励真气
		private var _q_rewards_zq: int;
		
		//任务奖励声望
		private var _q_rewards_prestige: int;
		
		//任务奖励绑定元宝
		private var _q_rewards_bindyuanbao: int;
		
		//互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		private var _q_rewards_goods: String;
		
		//男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		private var _q_man_rewards_goods: String;
		
		//女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		private var _q_women_rewards_goods: String;
		
		//是否允许被吞噬（0不允许，1允许）
		private var _q_devour_able: int;
		
		//被吞噬后的奖励合并比例幅度
		private var _q_devour_prop: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//讨伐任务编号
			_q_id = readInt();
			//玩家等级区间min
			_q_mingrade = readInt();
			//玩家等级区间max
			_q_maxgrade = readInt();
			//卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
			_q_scroll_type = readInt();
			//美术图片资源编号
			_q_image = readInt();
			//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
			_q_monstercount = readString();
			//任务接取时需检测达成某成就序列（成就编号;成就编号）
			_q_accept_needAchieve = readString();
			//接取任务时发送的屏幕提示信息文字
			_q_accept_prompt = readString();
			//任务奖励达成某成就序列（成就编号;成就编号）
			_q_rewards_achieve = readString();
			//任务奖励经验
			_q_rewards_exp = readInt();
			//任务奖励铜钱
			_q_rewards_coin = readInt();
			//任务奖励真气
			_q_rewards_zq = readInt();
			//任务奖励声望
			_q_rewards_prestige = readInt();
			//任务奖励绑定元宝
			_q_rewards_bindyuanbao = readInt();
			//互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
			_q_rewards_goods = readString();
			//男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
			_q_man_rewards_goods = readString();
			//女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
			_q_women_rewards_goods = readString();
			//是否允许被吞噬（0不允许，1允许）
			_q_devour_able = readInt();
			//被吞噬后的奖励合并比例幅度
			_q_devour_prop = readInt();
			return true;
		}
		
		/**
		 * get 讨伐任务编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 讨伐任务编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 玩家等级区间min
		 * @return 
		 */
		public function get q_mingrade(): int{
			return _q_mingrade;
		}
		
		/**
		 * set 玩家等级区间min
		 */
		public function set q_mingrade(value: int): void{
			this._q_mingrade = value;
		}
		
		/**
		 * get 玩家等级区间max
		 * @return 
		 */
		public function get q_maxgrade(): int{
			return _q_maxgrade;
		}
		
		/**
		 * set 玩家等级区间max
		 */
		public function set q_maxgrade(value: int): void{
			this._q_maxgrade = value;
		}
		
		/**
		 * get 卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
		 * @return 
		 */
		public function get q_scroll_type(): int{
			return _q_scroll_type;
		}
		
		/**
		 * set 卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
		 */
		public function set q_scroll_type(value: int): void{
			this._q_scroll_type = value;
		}
		
		/**
		 * get 美术图片资源编号
		 * @return 
		 */
		public function get q_image(): int{
			return _q_image;
		}
		
		/**
		 * set 美术图片资源编号
		 */
		public function set q_image(value: int): void{
			this._q_image = value;
		}
		
		/**
		 * get 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		 * @return 
		 */
		public function get q_monstercount(): String{
			return _q_monstercount;
		}
		
		/**
		 * set 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		 */
		public function set q_monstercount(value: String): void{
			this._q_monstercount = value;
		}
		
		/**
		 * get 任务接取时需检测达成某成就序列（成就编号;成就编号）
		 * @return 
		 */
		public function get q_accept_needAchieve(): String{
			return _q_accept_needAchieve;
		}
		
		/**
		 * set 任务接取时需检测达成某成就序列（成就编号;成就编号）
		 */
		public function set q_accept_needAchieve(value: String): void{
			this._q_accept_needAchieve = value;
		}
		
		/**
		 * get 接取任务时发送的屏幕提示信息文字
		 * @return 
		 */
		public function get q_accept_prompt(): String{
			return _q_accept_prompt;
		}
		
		/**
		 * set 接取任务时发送的屏幕提示信息文字
		 */
		public function set q_accept_prompt(value: String): void{
			this._q_accept_prompt = value;
		}
		
		/**
		 * get 任务奖励达成某成就序列（成就编号;成就编号）
		 * @return 
		 */
		public function get q_rewards_achieve(): String{
			return _q_rewards_achieve;
		}
		
		/**
		 * set 任务奖励达成某成就序列（成就编号;成就编号）
		 */
		public function set q_rewards_achieve(value: String): void{
			this._q_rewards_achieve = value;
		}
		
		/**
		 * get 任务奖励经验
		 * @return 
		 */
		public function get q_rewards_exp(): int{
			return _q_rewards_exp;
		}
		
		/**
		 * set 任务奖励经验
		 */
		public function set q_rewards_exp(value: int): void{
			this._q_rewards_exp = value;
		}
		
		/**
		 * get 任务奖励铜钱
		 * @return 
		 */
		public function get q_rewards_coin(): int{
			return _q_rewards_coin;
		}
		
		/**
		 * set 任务奖励铜钱
		 */
		public function set q_rewards_coin(value: int): void{
			this._q_rewards_coin = value;
		}
		
		/**
		 * get 任务奖励真气
		 * @return 
		 */
		public function get q_rewards_zq(): int{
			return _q_rewards_zq;
		}
		
		/**
		 * set 任务奖励真气
		 */
		public function set q_rewards_zq(value: int): void{
			this._q_rewards_zq = value;
		}
		
		/**
		 * get 任务奖励声望
		 * @return 
		 */
		public function get q_rewards_prestige(): int{
			return _q_rewards_prestige;
		}
		
		/**
		 * set 任务奖励声望
		 */
		public function set q_rewards_prestige(value: int): void{
			this._q_rewards_prestige = value;
		}
		
		/**
		 * get 任务奖励绑定元宝
		 * @return 
		 */
		public function get q_rewards_bindyuanbao(): int{
			return _q_rewards_bindyuanbao;
		}
		
		/**
		 * set 任务奖励绑定元宝
		 */
		public function set q_rewards_bindyuanbao(value: int): void{
			this._q_rewards_bindyuanbao = value;
		}
		
		/**
		 * get 互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 * @return 
		 */
		public function get q_rewards_goods(): String{
			return _q_rewards_goods;
		}
		
		/**
		 * set 互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 */
		public function set q_rewards_goods(value: String): void{
			this._q_rewards_goods = value;
		}
		
		/**
		 * get 男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 * @return 
		 */
		public function get q_man_rewards_goods(): String{
			return _q_man_rewards_goods;
		}
		
		/**
		 * set 男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 */
		public function set q_man_rewards_goods(value: String): void{
			this._q_man_rewards_goods = value;
		}
		
		/**
		 * get 女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 * @return 
		 */
		public function get q_women_rewards_goods(): String{
			return _q_women_rewards_goods;
		}
		
		/**
		 * set 女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		 */
		public function set q_women_rewards_goods(value: String): void{
			this._q_women_rewards_goods = value;
		}
		
		/**
		 * get 是否允许被吞噬（0不允许，1允许）
		 * @return 
		 */
		public function get q_devour_able(): int{
			return _q_devour_able;
		}
		
		/**
		 * set 是否允许被吞噬（0不允许，1允许）
		 */
		public function set q_devour_able(value: int): void{
			this._q_devour_able = value;
		}
		
		/**
		 * get 被吞噬后的奖励合并比例幅度
		 * @return 
		 */
		public function get q_devour_prop(): int{
			return _q_devour_prop;
		}
		
		/**
		 * set 被吞噬后的奖励合并比例幅度
		 */
		public function set q_devour_prop(value: int): void{
			this._q_devour_prop = value;
		}
		
	}
}