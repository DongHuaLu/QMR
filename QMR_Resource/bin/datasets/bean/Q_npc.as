package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_npc
	 */
	public class Q_npc extends Bean{
	
		//NPC编号
		private var _q_id: int;
		
		//刷新地图ID
		private var _q_map: int;
		
		//刷新X坐标
		private var _q_x: int;
		
		//刷新Y坐标
		private var _q_y: int;
		
		//NPC名字
		private var _q_name: String;
		
		//NPC称号
		private var _q_title: String;
		
		//NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
		private var _q_function: String;
		
		//NPC功能描述（小地图tips描述，支持html）
		private var _q_npcdesc: String;
		
		//0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
		private var _q_isminimap: int;
		
		//NPC绑定的商店出售模板编号
		private var _q_shop: int;
		
		//NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
		private var _q_transfer: String;
		
		//NPC身上绑定的任务编号列表
		private var _q_task: String;
		
		//NPC功能面板上的简单对白（需支持基本的Html语法）
		private var _q_dialog: String;
		
		//NPC在场景中的发言内容(多句以分号分隔)
		private var _q_speak: String;
		
		//发言频率间隔(单位：毫秒)
		private var _q_speak_time: int;
		
		//资源类型（0默认，1怪物，2侍宠）
		private var _q_resources: int;
		
		//造型是否翻转（0否，1是）
		private var _q_isturn: int;
		
		//默认朝向（0开始，顺时针转到7）
		private var _q_direction: int;
		
		//造型资源编号（资源使用：单方向6帧）
		private var _q_resource: String;
		
		//头像资源编号
		private var _q_head: String;
		
		//任务接取播放（任务ID+动作名）
		private var _q_access_play: String;
		
		//任务完成播放（任务号+动作名）
		private var _q_complete_play: String;
		
		//是否可见（0，1）
		private var _q_isvisible: int;
		
		//NPC默认行为
		private var _q_behavior: int;
		
		//是否跟随玩家
		private var _q_follow: int;
		
		//点击播放动作
		private var _q_clickplay: int;
		
		//玩家状态
		private var _q_playerstate: int;
		
		//点击触发脚本编号
		private var _q_clickscriptid: int;
		
		//进入视野播放动作
		private var _q_viewplay: int;
		
		//npc视野范围
		private var _q_field: int;
		
		//采集后触发脚本编号
		private var _q_collectscript: int;
		
		//采集时间（毫秒）
		private var _q_collecttime: int;
		
		//是否显示NPC
		private var _q_displaynpc: String;
		
		//采集获得物品
		private var _q_acquisition_item: int;
		
		//npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
		private var _q_type: int;
		
		//像素X坐标
		private var _q_px: int;
		
		//像素Y坐标
		private var _q_py: int;
		
		//采集完成任务
		private var _q_acquisition_task: int;
		
		//npc交互距离
		private var _q_Interactive: int;
		
		//npcAI
		private var _q_ai: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//NPC编号
			_q_id = readInt();
			//刷新地图ID
			_q_map = readInt();
			//刷新X坐标
			_q_x = readInt();
			//刷新Y坐标
			_q_y = readInt();
			//NPC名字
			_q_name = readString();
			//NPC称号
			_q_title = readString();
			//NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
			_q_function = readString();
			//NPC功能描述（小地图tips描述，支持html）
			_q_npcdesc = readString();
			//0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
			_q_isminimap = readInt();
			//NPC绑定的商店出售模板编号
			_q_shop = readInt();
			//NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
			_q_transfer = readString();
			//NPC身上绑定的任务编号列表
			_q_task = readString();
			//NPC功能面板上的简单对白（需支持基本的Html语法）
			_q_dialog = readString();
			//NPC在场景中的发言内容(多句以分号分隔)
			_q_speak = readString();
			//发言频率间隔(单位：毫秒)
			_q_speak_time = readInt();
			//资源类型（0默认，1怪物，2侍宠）
			_q_resources = readInt();
			//造型是否翻转（0否，1是）
			_q_isturn = readInt();
			//默认朝向（0开始，顺时针转到7）
			_q_direction = readInt();
			//造型资源编号（资源使用：单方向6帧）
			_q_resource = readString();
			//头像资源编号
			_q_head = readString();
			//任务接取播放（任务ID+动作名）
			_q_access_play = readString();
			//任务完成播放（任务号+动作名）
			_q_complete_play = readString();
			//是否可见（0，1）
			_q_isvisible = readInt();
			//NPC默认行为
			_q_behavior = readInt();
			//是否跟随玩家
			_q_follow = readInt();
			//点击播放动作
			_q_clickplay = readInt();
			//玩家状态
			_q_playerstate = readInt();
			//点击触发脚本编号
			_q_clickscriptid = readInt();
			//进入视野播放动作
			_q_viewplay = readInt();
			//npc视野范围
			_q_field = readInt();
			//采集后触发脚本编号
			_q_collectscript = readInt();
			//采集时间（毫秒）
			_q_collecttime = readInt();
			//是否显示NPC
			_q_displaynpc = readString();
			//采集获得物品
			_q_acquisition_item = readInt();
			//npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
			_q_type = readInt();
			//像素X坐标
			_q_px = readInt();
			//像素Y坐标
			_q_py = readInt();
			//采集完成任务
			_q_acquisition_task = readInt();
			//npc交互距离
			_q_Interactive = readInt();
			//npcAI
			_q_ai = readInt();
			return true;
		}
		
		/**
		 * get NPC编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set NPC编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 刷新地图ID
		 * @return 
		 */
		public function get q_map(): int{
			return _q_map;
		}
		
		/**
		 * set 刷新地图ID
		 */
		public function set q_map(value: int): void{
			this._q_map = value;
		}
		
		/**
		 * get 刷新X坐标
		 * @return 
		 */
		public function get q_x(): int{
			return _q_x;
		}
		
		/**
		 * set 刷新X坐标
		 */
		public function set q_x(value: int): void{
			this._q_x = value;
		}
		
		/**
		 * get 刷新Y坐标
		 * @return 
		 */
		public function get q_y(): int{
			return _q_y;
		}
		
		/**
		 * set 刷新Y坐标
		 */
		public function set q_y(value: int): void{
			this._q_y = value;
		}
		
		/**
		 * get NPC名字
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set NPC名字
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get NPC称号
		 * @return 
		 */
		public function get q_title(): String{
			return _q_title;
		}
		
		/**
		 * set NPC称号
		 */
		public function set q_title(value: String): void{
			this._q_title = value;
		}
		
		/**
		 * get NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
		 * @return 
		 */
		public function get q_function(): String{
			return _q_function;
		}
		
		/**
		 * set NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
		 */
		public function set q_function(value: String): void{
			this._q_function = value;
		}
		
		/**
		 * get NPC功能描述（小地图tips描述，支持html）
		 * @return 
		 */
		public function get q_npcdesc(): String{
			return _q_npcdesc;
		}
		
		/**
		 * set NPC功能描述（小地图tips描述，支持html）
		 */
		public function set q_npcdesc(value: String): void{
			this._q_npcdesc = value;
		}
		
		/**
		 * get 0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
		 * @return 
		 */
		public function get q_isminimap(): int{
			return _q_isminimap;
		}
		
		/**
		 * set 0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
		 */
		public function set q_isminimap(value: int): void{
			this._q_isminimap = value;
		}
		
		/**
		 * get NPC绑定的商店出售模板编号
		 * @return 
		 */
		public function get q_shop(): int{
			return _q_shop;
		}
		
		/**
		 * set NPC绑定的商店出售模板编号
		 */
		public function set q_shop(value: int): void{
			this._q_shop = value;
		}
		
		/**
		 * get NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
		 * @return 
		 */
		public function get q_transfer(): String{
			return _q_transfer;
		}
		
		/**
		 * set NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
		 */
		public function set q_transfer(value: String): void{
			this._q_transfer = value;
		}
		
		/**
		 * get NPC身上绑定的任务编号列表
		 * @return 
		 */
		public function get q_task(): String{
			return _q_task;
		}
		
		/**
		 * set NPC身上绑定的任务编号列表
		 */
		public function set q_task(value: String): void{
			this._q_task = value;
		}
		
		/**
		 * get NPC功能面板上的简单对白（需支持基本的Html语法）
		 * @return 
		 */
		public function get q_dialog(): String{
			return _q_dialog;
		}
		
		/**
		 * set NPC功能面板上的简单对白（需支持基本的Html语法）
		 */
		public function set q_dialog(value: String): void{
			this._q_dialog = value;
		}
		
		/**
		 * get NPC在场景中的发言内容(多句以分号分隔)
		 * @return 
		 */
		public function get q_speak(): String{
			return _q_speak;
		}
		
		/**
		 * set NPC在场景中的发言内容(多句以分号分隔)
		 */
		public function set q_speak(value: String): void{
			this._q_speak = value;
		}
		
		/**
		 * get 发言频率间隔(单位：毫秒)
		 * @return 
		 */
		public function get q_speak_time(): int{
			return _q_speak_time;
		}
		
		/**
		 * set 发言频率间隔(单位：毫秒)
		 */
		public function set q_speak_time(value: int): void{
			this._q_speak_time = value;
		}
		
		/**
		 * get 资源类型（0默认，1怪物，2侍宠）
		 * @return 
		 */
		public function get q_resources(): int{
			return _q_resources;
		}
		
		/**
		 * set 资源类型（0默认，1怪物，2侍宠）
		 */
		public function set q_resources(value: int): void{
			this._q_resources = value;
		}
		
		/**
		 * get 造型是否翻转（0否，1是）
		 * @return 
		 */
		public function get q_isturn(): int{
			return _q_isturn;
		}
		
		/**
		 * set 造型是否翻转（0否，1是）
		 */
		public function set q_isturn(value: int): void{
			this._q_isturn = value;
		}
		
		/**
		 * get 默认朝向（0开始，顺时针转到7）
		 * @return 
		 */
		public function get q_direction(): int{
			return _q_direction;
		}
		
		/**
		 * set 默认朝向（0开始，顺时针转到7）
		 */
		public function set q_direction(value: int): void{
			this._q_direction = value;
		}
		
		/**
		 * get 造型资源编号（资源使用：单方向6帧）
		 * @return 
		 */
		public function get q_resource(): String{
			return _q_resource;
		}
		
		/**
		 * set 造型资源编号（资源使用：单方向6帧）
		 */
		public function set q_resource(value: String): void{
			this._q_resource = value;
		}
		
		/**
		 * get 头像资源编号
		 * @return 
		 */
		public function get q_head(): String{
			return _q_head;
		}
		
		/**
		 * set 头像资源编号
		 */
		public function set q_head(value: String): void{
			this._q_head = value;
		}
		
		/**
		 * get 任务接取播放（任务ID+动作名）
		 * @return 
		 */
		public function get q_access_play(): String{
			return _q_access_play;
		}
		
		/**
		 * set 任务接取播放（任务ID+动作名）
		 */
		public function set q_access_play(value: String): void{
			this._q_access_play = value;
		}
		
		/**
		 * get 任务完成播放（任务号+动作名）
		 * @return 
		 */
		public function get q_complete_play(): String{
			return _q_complete_play;
		}
		
		/**
		 * set 任务完成播放（任务号+动作名）
		 */
		public function set q_complete_play(value: String): void{
			this._q_complete_play = value;
		}
		
		/**
		 * get 是否可见（0，1）
		 * @return 
		 */
		public function get q_isvisible(): int{
			return _q_isvisible;
		}
		
		/**
		 * set 是否可见（0，1）
		 */
		public function set q_isvisible(value: int): void{
			this._q_isvisible = value;
		}
		
		/**
		 * get NPC默认行为
		 * @return 
		 */
		public function get q_behavior(): int{
			return _q_behavior;
		}
		
		/**
		 * set NPC默认行为
		 */
		public function set q_behavior(value: int): void{
			this._q_behavior = value;
		}
		
		/**
		 * get 是否跟随玩家
		 * @return 
		 */
		public function get q_follow(): int{
			return _q_follow;
		}
		
		/**
		 * set 是否跟随玩家
		 */
		public function set q_follow(value: int): void{
			this._q_follow = value;
		}
		
		/**
		 * get 点击播放动作
		 * @return 
		 */
		public function get q_clickplay(): int{
			return _q_clickplay;
		}
		
		/**
		 * set 点击播放动作
		 */
		public function set q_clickplay(value: int): void{
			this._q_clickplay = value;
		}
		
		/**
		 * get 玩家状态
		 * @return 
		 */
		public function get q_playerstate(): int{
			return _q_playerstate;
		}
		
		/**
		 * set 玩家状态
		 */
		public function set q_playerstate(value: int): void{
			this._q_playerstate = value;
		}
		
		/**
		 * get 点击触发脚本编号
		 * @return 
		 */
		public function get q_clickscriptid(): int{
			return _q_clickscriptid;
		}
		
		/**
		 * set 点击触发脚本编号
		 */
		public function set q_clickscriptid(value: int): void{
			this._q_clickscriptid = value;
		}
		
		/**
		 * get 进入视野播放动作
		 * @return 
		 */
		public function get q_viewplay(): int{
			return _q_viewplay;
		}
		
		/**
		 * set 进入视野播放动作
		 */
		public function set q_viewplay(value: int): void{
			this._q_viewplay = value;
		}
		
		/**
		 * get npc视野范围
		 * @return 
		 */
		public function get q_field(): int{
			return _q_field;
		}
		
		/**
		 * set npc视野范围
		 */
		public function set q_field(value: int): void{
			this._q_field = value;
		}
		
		/**
		 * get 采集后触发脚本编号
		 * @return 
		 */
		public function get q_collectscript(): int{
			return _q_collectscript;
		}
		
		/**
		 * set 采集后触发脚本编号
		 */
		public function set q_collectscript(value: int): void{
			this._q_collectscript = value;
		}
		
		/**
		 * get 采集时间（毫秒）
		 * @return 
		 */
		public function get q_collecttime(): int{
			return _q_collecttime;
		}
		
		/**
		 * set 采集时间（毫秒）
		 */
		public function set q_collecttime(value: int): void{
			this._q_collecttime = value;
		}
		
		/**
		 * get 是否显示NPC
		 * @return 
		 */
		public function get q_displaynpc(): String{
			return _q_displaynpc;
		}
		
		/**
		 * set 是否显示NPC
		 */
		public function set q_displaynpc(value: String): void{
			this._q_displaynpc = value;
		}
		
		/**
		 * get 采集获得物品
		 * @return 
		 */
		public function get q_acquisition_item(): int{
			return _q_acquisition_item;
		}
		
		/**
		 * set 采集获得物品
		 */
		public function set q_acquisition_item(value: int): void{
			this._q_acquisition_item = value;
		}
		
		/**
		 * get npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 像素X坐标
		 * @return 
		 */
		public function get q_px(): int{
			return _q_px;
		}
		
		/**
		 * set 像素X坐标
		 */
		public function set q_px(value: int): void{
			this._q_px = value;
		}
		
		/**
		 * get 像素Y坐标
		 * @return 
		 */
		public function get q_py(): int{
			return _q_py;
		}
		
		/**
		 * set 像素Y坐标
		 */
		public function set q_py(value: int): void{
			this._q_py = value;
		}
		
		/**
		 * get 采集完成任务
		 * @return 
		 */
		public function get q_acquisition_task(): int{
			return _q_acquisition_task;
		}
		
		/**
		 * set 采集完成任务
		 */
		public function set q_acquisition_task(value: int): void{
			this._q_acquisition_task = value;
		}
		
		/**
		 * get npc交互距离
		 * @return 
		 */
		public function get q_Interactive(): int{
			return _q_Interactive;
		}
		
		/**
		 * set npc交互距离
		 */
		public function set q_Interactive(value: int): void{
			this._q_Interactive = value;
		}
		
		/**
		 * get npcAI
		 * @return 
		 */
		public function get q_ai(): int{
			return _q_ai;
		}
		
		/**
		 * set npcAI
		 */
		public function set q_ai(value: int): void{
			this._q_ai = value;
		}
		
	}
}