package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_shop
	 */
	public class Q_shop extends Bean{
	
		//本表索引编号（会影响到该物品在标签页中的排序）
		private var _q_id: int;
		
		//商店出售模板编号
		private var _q_model_id: int;
		
		//商店类型（0随身商店，1NPC商店，2元宝道具商城）
		private var _q_shop_type: int;
		
		//商店标题
		private var _q_shop_name: String;
		
		//所属商店子页编号
		private var _q_page: int;
		
		//商店子页标签名称
		private var _q_page_name: String;
		
		//标签页开放性别需求（0通用，1男，2女）
		private var _q_page_sex: int;
		
		//出售物品ID
		private var _q_sell: int;
		
		//物品强化等级定义
		private var _q_strengthen: int;
		
		//物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
		private var _q_append: String;
		
		//购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
		private var _q_losttime: String;
		
		//购买后离自动失效前的存在时间（单位：秒）
		private var _q_duration: int;
		
		//购买时是否立刻绑定（0不是，1是立刻绑定）
		private var _q_buy_bind: int;
		
		//允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
		private var _q_money_type: int;
		
		//游戏币价格修正为
		private var _q_coin: int;
		
		//元宝价格
		private var _q_gold: int;
		
		//绑定元宝价格
		private var _q_bindgold: int;
		
		//游戏币原价显示为
		private var _q_show_coin: int;
		
		//元宝原价显示为
		private var _q_show_gold: int;
		
		//绑定元宝原价显示为
		private var _q_show_bindgold: int;
		
		//物品在商城面板中的文字描述（需支持html语法）
		private var _q_describe: String;
		
		//热销标识(0无热销，1热销中，2折扣，3热销+折扣)
		private var _q_hot: int;
		
		//上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
		private var _q_rack: String;
		
		//显示所需人物等级
		private var _q_show_level: int;
		
		//显示所需人物累计消耗元宝数
		private var _q_show_cost: int;
		
		//打折比例(百分比)
		private var _q_sale_rate: int;
		
		//打折时间表达式
		private var _q_discount_time: String;
		
		//显示顺序
		private var _q_index: int;
		
		//开服时间打折表达式
		private var _q_openserver_discount: String;
		
		//开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
		private var _q_openserver_rack: String;
		
		//影响区服字段    区服；区服-区服；区服-&（无限大）
		private var _q_service_area: String;
		
		//限购上限
		private var _q_shop_limit: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//本表索引编号（会影响到该物品在标签页中的排序）
			_q_id = readInt();
			//商店出售模板编号
			_q_model_id = readInt();
			//商店类型（0随身商店，1NPC商店，2元宝道具商城）
			_q_shop_type = readInt();
			//商店标题
			_q_shop_name = readString();
			//所属商店子页编号
			_q_page = readInt();
			//商店子页标签名称
			_q_page_name = readString();
			//标签页开放性别需求（0通用，1男，2女）
			_q_page_sex = readInt();
			//出售物品ID
			_q_sell = readInt();
			//物品强化等级定义
			_q_strengthen = readInt();
			//物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
			_q_append = readString();
			//购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
			_q_losttime = readString();
			//购买后离自动失效前的存在时间（单位：秒）
			_q_duration = readInt();
			//购买时是否立刻绑定（0不是，1是立刻绑定）
			_q_buy_bind = readInt();
			//允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
			_q_money_type = readInt();
			//游戏币价格修正为
			_q_coin = readInt();
			//元宝价格
			_q_gold = readInt();
			//绑定元宝价格
			_q_bindgold = readInt();
			//游戏币原价显示为
			_q_show_coin = readInt();
			//元宝原价显示为
			_q_show_gold = readInt();
			//绑定元宝原价显示为
			_q_show_bindgold = readInt();
			//物品在商城面板中的文字描述（需支持html语法）
			_q_describe = readString();
			//热销标识(0无热销，1热销中，2折扣，3热销+折扣)
			_q_hot = readInt();
			//上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
			_q_rack = readString();
			//显示所需人物等级
			_q_show_level = readInt();
			//显示所需人物累计消耗元宝数
			_q_show_cost = readInt();
			//打折比例(百分比)
			_q_sale_rate = readInt();
			//打折时间表达式
			_q_discount_time = readString();
			//显示顺序
			_q_index = readInt();
			//开服时间打折表达式
			_q_openserver_discount = readString();
			//开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
			_q_openserver_rack = readString();
			//影响区服字段    区服；区服-区服；区服-&（无限大）
			_q_service_area = readString();
			//限购上限
			_q_shop_limit = readInt();
			return true;
		}
		
		/**
		 * get 本表索引编号（会影响到该物品在标签页中的排序）
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 本表索引编号（会影响到该物品在标签页中的排序）
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 商店出售模板编号
		 * @return 
		 */
		public function get q_model_id(): int{
			return _q_model_id;
		}
		
		/**
		 * set 商店出售模板编号
		 */
		public function set q_model_id(value: int): void{
			this._q_model_id = value;
		}
		
		/**
		 * get 商店类型（0随身商店，1NPC商店，2元宝道具商城）
		 * @return 
		 */
		public function get q_shop_type(): int{
			return _q_shop_type;
		}
		
		/**
		 * set 商店类型（0随身商店，1NPC商店，2元宝道具商城）
		 */
		public function set q_shop_type(value: int): void{
			this._q_shop_type = value;
		}
		
		/**
		 * get 商店标题
		 * @return 
		 */
		public function get q_shop_name(): String{
			return _q_shop_name;
		}
		
		/**
		 * set 商店标题
		 */
		public function set q_shop_name(value: String): void{
			this._q_shop_name = value;
		}
		
		/**
		 * get 所属商店子页编号
		 * @return 
		 */
		public function get q_page(): int{
			return _q_page;
		}
		
		/**
		 * set 所属商店子页编号
		 */
		public function set q_page(value: int): void{
			this._q_page = value;
		}
		
		/**
		 * get 商店子页标签名称
		 * @return 
		 */
		public function get q_page_name(): String{
			return _q_page_name;
		}
		
		/**
		 * set 商店子页标签名称
		 */
		public function set q_page_name(value: String): void{
			this._q_page_name = value;
		}
		
		/**
		 * get 标签页开放性别需求（0通用，1男，2女）
		 * @return 
		 */
		public function get q_page_sex(): int{
			return _q_page_sex;
		}
		
		/**
		 * set 标签页开放性别需求（0通用，1男，2女）
		 */
		public function set q_page_sex(value: int): void{
			this._q_page_sex = value;
		}
		
		/**
		 * get 出售物品ID
		 * @return 
		 */
		public function get q_sell(): int{
			return _q_sell;
		}
		
		/**
		 * set 出售物品ID
		 */
		public function set q_sell(value: int): void{
			this._q_sell = value;
		}
		
		/**
		 * get 物品强化等级定义
		 * @return 
		 */
		public function get q_strengthen(): int{
			return _q_strengthen;
		}
		
		/**
		 * set 物品强化等级定义
		 */
		public function set q_strengthen(value: int): void{
			this._q_strengthen = value;
		}
		
		/**
		 * get 物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
		 * @return 
		 */
		public function get q_append(): String{
			return _q_append;
		}
		
		/**
		 * set 物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
		 */
		public function set q_append(value: String): void{
			this._q_append = value;
		}
		
		/**
		 * get 购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
		 * @return 
		 */
		public function get q_losttime(): String{
			return _q_losttime;
		}
		
		/**
		 * set 购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
		 */
		public function set q_losttime(value: String): void{
			this._q_losttime = value;
		}
		
		/**
		 * get 购买后离自动失效前的存在时间（单位：秒）
		 * @return 
		 */
		public function get q_duration(): int{
			return _q_duration;
		}
		
		/**
		 * set 购买后离自动失效前的存在时间（单位：秒）
		 */
		public function set q_duration(value: int): void{
			this._q_duration = value;
		}
		
		/**
		 * get 购买时是否立刻绑定（0不是，1是立刻绑定）
		 * @return 
		 */
		public function get q_buy_bind(): int{
			return _q_buy_bind;
		}
		
		/**
		 * set 购买时是否立刻绑定（0不是，1是立刻绑定）
		 */
		public function set q_buy_bind(value: int): void{
			this._q_buy_bind = value;
		}
		
		/**
		 * get 允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
		 * @return 
		 */
		public function get q_money_type(): int{
			return _q_money_type;
		}
		
		/**
		 * set 允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
		 */
		public function set q_money_type(value: int): void{
			this._q_money_type = value;
		}
		
		/**
		 * get 游戏币价格修正为
		 * @return 
		 */
		public function get q_coin(): int{
			return _q_coin;
		}
		
		/**
		 * set 游戏币价格修正为
		 */
		public function set q_coin(value: int): void{
			this._q_coin = value;
		}
		
		/**
		 * get 元宝价格
		 * @return 
		 */
		public function get q_gold(): int{
			return _q_gold;
		}
		
		/**
		 * set 元宝价格
		 */
		public function set q_gold(value: int): void{
			this._q_gold = value;
		}
		
		/**
		 * get 绑定元宝价格
		 * @return 
		 */
		public function get q_bindgold(): int{
			return _q_bindgold;
		}
		
		/**
		 * set 绑定元宝价格
		 */
		public function set q_bindgold(value: int): void{
			this._q_bindgold = value;
		}
		
		/**
		 * get 游戏币原价显示为
		 * @return 
		 */
		public function get q_show_coin(): int{
			return _q_show_coin;
		}
		
		/**
		 * set 游戏币原价显示为
		 */
		public function set q_show_coin(value: int): void{
			this._q_show_coin = value;
		}
		
		/**
		 * get 元宝原价显示为
		 * @return 
		 */
		public function get q_show_gold(): int{
			return _q_show_gold;
		}
		
		/**
		 * set 元宝原价显示为
		 */
		public function set q_show_gold(value: int): void{
			this._q_show_gold = value;
		}
		
		/**
		 * get 绑定元宝原价显示为
		 * @return 
		 */
		public function get q_show_bindgold(): int{
			return _q_show_bindgold;
		}
		
		/**
		 * set 绑定元宝原价显示为
		 */
		public function set q_show_bindgold(value: int): void{
			this._q_show_bindgold = value;
		}
		
		/**
		 * get 物品在商城面板中的文字描述（需支持html语法）
		 * @return 
		 */
		public function get q_describe(): String{
			return _q_describe;
		}
		
		/**
		 * set 物品在商城面板中的文字描述（需支持html语法）
		 */
		public function set q_describe(value: String): void{
			this._q_describe = value;
		}
		
		/**
		 * get 热销标识(0无热销，1热销中，2折扣，3热销+折扣)
		 * @return 
		 */
		public function get q_hot(): int{
			return _q_hot;
		}
		
		/**
		 * set 热销标识(0无热销，1热销中，2折扣，3热销+折扣)
		 */
		public function set q_hot(value: int): void{
			this._q_hot = value;
		}
		
		/**
		 * get 上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
		 * @return 
		 */
		public function get q_rack(): String{
			return _q_rack;
		}
		
		/**
		 * set 上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
		 */
		public function set q_rack(value: String): void{
			this._q_rack = value;
		}
		
		/**
		 * get 显示所需人物等级
		 * @return 
		 */
		public function get q_show_level(): int{
			return _q_show_level;
		}
		
		/**
		 * set 显示所需人物等级
		 */
		public function set q_show_level(value: int): void{
			this._q_show_level = value;
		}
		
		/**
		 * get 显示所需人物累计消耗元宝数
		 * @return 
		 */
		public function get q_show_cost(): int{
			return _q_show_cost;
		}
		
		/**
		 * set 显示所需人物累计消耗元宝数
		 */
		public function set q_show_cost(value: int): void{
			this._q_show_cost = value;
		}
		
		/**
		 * get 打折比例(百分比)
		 * @return 
		 */
		public function get q_sale_rate(): int{
			return _q_sale_rate;
		}
		
		/**
		 * set 打折比例(百分比)
		 */
		public function set q_sale_rate(value: int): void{
			this._q_sale_rate = value;
		}
		
		/**
		 * get 打折时间表达式
		 * @return 
		 */
		public function get q_discount_time(): String{
			return _q_discount_time;
		}
		
		/**
		 * set 打折时间表达式
		 */
		public function set q_discount_time(value: String): void{
			this._q_discount_time = value;
		}
		
		/**
		 * get 显示顺序
		 * @return 
		 */
		public function get q_index(): int{
			return _q_index;
		}
		
		/**
		 * set 显示顺序
		 */
		public function set q_index(value: int): void{
			this._q_index = value;
		}
		
		/**
		 * get 开服时间打折表达式
		 * @return 
		 */
		public function get q_openserver_discount(): String{
			return _q_openserver_discount;
		}
		
		/**
		 * set 开服时间打折表达式
		 */
		public function set q_openserver_discount(value: String): void{
			this._q_openserver_discount = value;
		}
		
		/**
		 * get 开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
		 * @return 
		 */
		public function get q_openserver_rack(): String{
			return _q_openserver_rack;
		}
		
		/**
		 * set 开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
		 */
		public function set q_openserver_rack(value: String): void{
			this._q_openserver_rack = value;
		}
		
		/**
		 * get 影响区服字段    区服；区服-区服；区服-&（无限大）
		 * @return 
		 */
		public function get q_service_area(): String{
			return _q_service_area;
		}
		
		/**
		 * set 影响区服字段    区服；区服-区服；区服-&（无限大）
		 */
		public function set q_service_area(value: String): void{
			this._q_service_area = value;
		}
		
		/**
		 * get 限购上限
		 * @return 
		 */
		public function get q_shop_limit(): int{
			return _q_shop_limit;
		}
		
		/**
		 * set 限购上限
		 */
		public function set q_shop_limit(value: int): void{
			this._q_shop_limit = value;
		}
		
	}
}