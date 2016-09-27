package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_main
	 */
	public class Q_task_main extends Bean{
	
		//主线剧情大章节编号
		private var _q_chapter: int;
		
		//主线剧情大章节名称
		private var _q_chapter_name: String;
		
		//章节中的子任务编号
		private var _q_taskid: int;
		
		//子任务名称
		private var _q_name: String;
		
		//任务交付类型（1自动交付类任务，2在NPC处交付类任务）
		private var _q_finsh_type: int;
		
		//任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
		private var _q_task_type: int;
		
		//该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		private var _q_task_desc: String;
		
		//任务接取时发送的主界面对白
		private var _q_start_chat: String;
		
		//该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		private var _q_task_desc2: String;
		
		//完成任务NPC编号
		private var _q_endnpc: int;
		
		//任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
		private var _q_end_need_goods: String;
		
		//任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
		private var _q_end_need_killmonster: String;
		
		//任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
		private var _q_end_need_achieve: String;
		
		//完成时收取物品序列(物品ID_数量;物品ID_数量）
		private var _q_end_resume_goods: String;
		
		//完成后自动接取任务号
		private var _q_next_task: int;
		
		//任务接取时所需最小等级
		private var _q_accept_needmingrade: int;
		
		//任务奖励达成某成就序列（成就编号;成就编号）
		private var _q_rewards_achieve: String;
		
		//任务奖励经验
		private var _q_rewards_exp: int;
		
		//任务奖励铜钱
		private var _q_rewards_coin: int;
		
		//任务奖励真气
		private var _q_rewards_zq: int;
		
		//任务奖励功勋
		private var _q_rewards_exploit: int;
		
		//任务奖励声望
		private var _q_rewards_prestige: int;
		
		//任务奖励绑定元宝
		private var _q_rewards_bindYuanBao: int;
		
		//任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
		private var _q_rewards_goods: String;
		
		//接取任务刷新BOSS
		private var _q_taskfinlishboss: int;
		
		//自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
		private var _q_ispromp: int;
		
		//坐骑阶数
		private var _q_end_need_horselevel: int;
		
		//完成讨伐任务数
		private var _q_end_need_conquertaskcount: int;
		
		//完成日常任务数
		private var _q_end_need_dailytaskcount: int;
		
		//奖励侍宠
		private var _q_rewards_pet: int;
		
		//剧情BOSS编号
		private var _q_task_brush_monid: int;
		
		//任务刷怪地图
		private var _q_brush_mon_map: int;
		
		//任务刷怪坐标
		private var _q_brush_mon_xy: String;
		
		//接受时显示npc
		private var _q_acc_show: String;
		
		//接受时隐藏npc
		private var _q_acc_hide: String;
		
		//完成时显示npc
		private var _q_end_show: String;
		
		//完成时隐藏npc
		private var _q_end_hide: String;
		
		//是否需要完成动作（1是，非1即为否）
		private var _q_end_needaction: int;
		
		//奖励脚本
		private var _q_rewards_scrpt: int;
		
		//达成条件后显示
		private var _q_accomplish_show: String;
		
		//达成条件后隐藏
		private var _q_accomplish_hide: String;
		
		//采集目标NPC编号
		private var _q_collection_target: int;
		
		//完成任务显示怪物
		private var _q_end_show_moster: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//主线剧情大章节编号
			_q_chapter = readInt();
			//主线剧情大章节名称
			_q_chapter_name = readString();
			//章节中的子任务编号
			_q_taskid = readInt();
			//子任务名称
			_q_name = readString();
			//任务交付类型（1自动交付类任务，2在NPC处交付类任务）
			_q_finsh_type = readInt();
			//任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
			_q_task_type = readInt();
			//该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
			_q_task_desc = readString();
			//任务接取时发送的主界面对白
			_q_start_chat = readString();
			//该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
			_q_task_desc2 = readString();
			//完成任务NPC编号
			_q_endnpc = readInt();
			//任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
			_q_end_need_goods = readString();
			//任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
			_q_end_need_killmonster = readString();
			//任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
			_q_end_need_achieve = readString();
			//完成时收取物品序列(物品ID_数量;物品ID_数量）
			_q_end_resume_goods = readString();
			//完成后自动接取任务号
			_q_next_task = readInt();
			//任务接取时所需最小等级
			_q_accept_needmingrade = readInt();
			//任务奖励达成某成就序列（成就编号;成就编号）
			_q_rewards_achieve = readString();
			//任务奖励经验
			_q_rewards_exp = readInt();
			//任务奖励铜钱
			_q_rewards_coin = readInt();
			//任务奖励真气
			_q_rewards_zq = readInt();
			//任务奖励功勋
			_q_rewards_exploit = readInt();
			//任务奖励声望
			_q_rewards_prestige = readInt();
			//任务奖励绑定元宝
			_q_rewards_bindYuanBao = readInt();
			//任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
			_q_rewards_goods = readString();
			//接取任务刷新BOSS
			_q_taskfinlishboss = readInt();
			//自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
			_q_ispromp = readInt();
			//坐骑阶数
			_q_end_need_horselevel = readInt();
			//完成讨伐任务数
			_q_end_need_conquertaskcount = readInt();
			//完成日常任务数
			_q_end_need_dailytaskcount = readInt();
			//奖励侍宠
			_q_rewards_pet = readInt();
			//剧情BOSS编号
			_q_task_brush_monid = readInt();
			//任务刷怪地图
			_q_brush_mon_map = readInt();
			//任务刷怪坐标
			_q_brush_mon_xy = readString();
			//接受时显示npc
			_q_acc_show = readString();
			//接受时隐藏npc
			_q_acc_hide = readString();
			//完成时显示npc
			_q_end_show = readString();
			//完成时隐藏npc
			_q_end_hide = readString();
			//是否需要完成动作（1是，非1即为否）
			_q_end_needaction = readInt();
			//奖励脚本
			_q_rewards_scrpt = readInt();
			//达成条件后显示
			_q_accomplish_show = readString();
			//达成条件后隐藏
			_q_accomplish_hide = readString();
			//采集目标NPC编号
			_q_collection_target = readInt();
			//完成任务显示怪物
			_q_end_show_moster = readString();
			return true;
		}
		
		/**
		 * get 主线剧情大章节编号
		 * @return 
		 */
		public function get q_chapter(): int{
			return _q_chapter;
		}
		
		/**
		 * set 主线剧情大章节编号
		 */
		public function set q_chapter(value: int): void{
			this._q_chapter = value;
		}
		
		/**
		 * get 主线剧情大章节名称
		 * @return 
		 */
		public function get q_chapter_name(): String{
			return _q_chapter_name;
		}
		
		/**
		 * set 主线剧情大章节名称
		 */
		public function set q_chapter_name(value: String): void{
			this._q_chapter_name = value;
		}
		
		/**
		 * get 章节中的子任务编号
		 * @return 
		 */
		public function get q_taskid(): int{
			return _q_taskid;
		}
		
		/**
		 * set 章节中的子任务编号
		 */
		public function set q_taskid(value: int): void{
			this._q_taskid = value;
		}
		
		/**
		 * get 子任务名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 子任务名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 任务交付类型（1自动交付类任务，2在NPC处交付类任务）
		 * @return 
		 */
		public function get q_finsh_type(): int{
			return _q_finsh_type;
		}
		
		/**
		 * set 任务交付类型（1自动交付类任务，2在NPC处交付类任务）
		 */
		public function set q_finsh_type(value: int): void{
			this._q_finsh_type = value;
		}
		
		/**
		 * get 任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
		 * @return 
		 */
		public function get q_task_type(): int{
			return _q_task_type;
		}
		
		/**
		 * set 任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
		 */
		public function set q_task_type(value: int): void{
			this._q_task_type = value;
		}
		
		/**
		 * get 该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		 * @return 
		 */
		public function get q_task_desc(): String{
			return _q_task_desc;
		}
		
		/**
		 * set 该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		 */
		public function set q_task_desc(value: String): void{
			this._q_task_desc = value;
		}
		
		/**
		 * get 任务接取时发送的主界面对白
		 * @return 
		 */
		public function get q_start_chat(): String{
			return _q_start_chat;
		}
		
		/**
		 * set 任务接取时发送的主界面对白
		 */
		public function set q_start_chat(value: String): void{
			this._q_start_chat = value;
		}
		
		/**
		 * get 该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		 * @return 
		 */
		public function get q_task_desc2(): String{
			return _q_task_desc2;
		}
		
		/**
		 * set 该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
		 */
		public function set q_task_desc2(value: String): void{
			this._q_task_desc2 = value;
		}
		
		/**
		 * get 完成任务NPC编号
		 * @return 
		 */
		public function get q_endnpc(): int{
			return _q_endnpc;
		}
		
		/**
		 * set 完成任务NPC编号
		 */
		public function set q_endnpc(value: int): void{
			this._q_endnpc = value;
		}
		
		/**
		 * get 任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
		 * @return 
		 */
		public function get q_end_need_goods(): String{
			return _q_end_need_goods;
		}
		
		/**
		 * set 任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
		 */
		public function set q_end_need_goods(value: String): void{
			this._q_end_need_goods = value;
		}
		
		/**
		 * get 任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
		 * @return 
		 */
		public function get q_end_need_killmonster(): String{
			return _q_end_need_killmonster;
		}
		
		/**
		 * set 任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
		 */
		public function set q_end_need_killmonster(value: String): void{
			this._q_end_need_killmonster = value;
		}
		
		/**
		 * get 任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
		 * @return 
		 */
		public function get q_end_need_achieve(): String{
			return _q_end_need_achieve;
		}
		
		/**
		 * set 任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
		 */
		public function set q_end_need_achieve(value: String): void{
			this._q_end_need_achieve = value;
		}
		
		/**
		 * get 完成时收取物品序列(物品ID_数量;物品ID_数量）
		 * @return 
		 */
		public function get q_end_resume_goods(): String{
			return _q_end_resume_goods;
		}
		
		/**
		 * set 完成时收取物品序列(物品ID_数量;物品ID_数量）
		 */
		public function set q_end_resume_goods(value: String): void{
			this._q_end_resume_goods = value;
		}
		
		/**
		 * get 完成后自动接取任务号
		 * @return 
		 */
		public function get q_next_task(): int{
			return _q_next_task;
		}
		
		/**
		 * set 完成后自动接取任务号
		 */
		public function set q_next_task(value: int): void{
			this._q_next_task = value;
		}
		
		/**
		 * get 任务接取时所需最小等级
		 * @return 
		 */
		public function get q_accept_needmingrade(): int{
			return _q_accept_needmingrade;
		}
		
		/**
		 * set 任务接取时所需最小等级
		 */
		public function set q_accept_needmingrade(value: int): void{
			this._q_accept_needmingrade = value;
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
		 * get 任务奖励功勋
		 * @return 
		 */
		public function get q_rewards_exploit(): int{
			return _q_rewards_exploit;
		}
		
		/**
		 * set 任务奖励功勋
		 */
		public function set q_rewards_exploit(value: int): void{
			this._q_rewards_exploit = value;
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
		public function get q_rewards_bindYuanBao(): int{
			return _q_rewards_bindYuanBao;
		}
		
		/**
		 * set 任务奖励绑定元宝
		 */
		public function set q_rewards_bindYuanBao(value: int): void{
			this._q_rewards_bindYuanBao = value;
		}
		
		/**
		 * get 任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
		 * @return 
		 */
		public function get q_rewards_goods(): String{
			return _q_rewards_goods;
		}
		
		/**
		 * set 任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
		 */
		public function set q_rewards_goods(value: String): void{
			this._q_rewards_goods = value;
		}
		
		/**
		 * get 接取任务刷新BOSS
		 * @return 
		 */
		public function get q_taskfinlishboss(): int{
			return _q_taskfinlishboss;
		}
		
		/**
		 * set 接取任务刷新BOSS
		 */
		public function set q_taskfinlishboss(value: int): void{
			this._q_taskfinlishboss = value;
		}
		
		/**
		 * get 自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
		 * @return 
		 */
		public function get q_ispromp(): int{
			return _q_ispromp;
		}
		
		/**
		 * set 自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
		 */
		public function set q_ispromp(value: int): void{
			this._q_ispromp = value;
		}
		
		/**
		 * get 坐骑阶数
		 * @return 
		 */
		public function get q_end_need_horselevel(): int{
			return _q_end_need_horselevel;
		}
		
		/**
		 * set 坐骑阶数
		 */
		public function set q_end_need_horselevel(value: int): void{
			this._q_end_need_horselevel = value;
		}
		
		/**
		 * get 完成讨伐任务数
		 * @return 
		 */
		public function get q_end_need_conquertaskcount(): int{
			return _q_end_need_conquertaskcount;
		}
		
		/**
		 * set 完成讨伐任务数
		 */
		public function set q_end_need_conquertaskcount(value: int): void{
			this._q_end_need_conquertaskcount = value;
		}
		
		/**
		 * get 完成日常任务数
		 * @return 
		 */
		public function get q_end_need_dailytaskcount(): int{
			return _q_end_need_dailytaskcount;
		}
		
		/**
		 * set 完成日常任务数
		 */
		public function set q_end_need_dailytaskcount(value: int): void{
			this._q_end_need_dailytaskcount = value;
		}
		
		/**
		 * get 奖励侍宠
		 * @return 
		 */
		public function get q_rewards_pet(): int{
			return _q_rewards_pet;
		}
		
		/**
		 * set 奖励侍宠
		 */
		public function set q_rewards_pet(value: int): void{
			this._q_rewards_pet = value;
		}
		
		/**
		 * get 剧情BOSS编号
		 * @return 
		 */
		public function get q_task_brush_monid(): int{
			return _q_task_brush_monid;
		}
		
		/**
		 * set 剧情BOSS编号
		 */
		public function set q_task_brush_monid(value: int): void{
			this._q_task_brush_monid = value;
		}
		
		/**
		 * get 任务刷怪地图
		 * @return 
		 */
		public function get q_brush_mon_map(): int{
			return _q_brush_mon_map;
		}
		
		/**
		 * set 任务刷怪地图
		 */
		public function set q_brush_mon_map(value: int): void{
			this._q_brush_mon_map = value;
		}
		
		/**
		 * get 任务刷怪坐标
		 * @return 
		 */
		public function get q_brush_mon_xy(): String{
			return _q_brush_mon_xy;
		}
		
		/**
		 * set 任务刷怪坐标
		 */
		public function set q_brush_mon_xy(value: String): void{
			this._q_brush_mon_xy = value;
		}
		
		/**
		 * get 接受时显示npc
		 * @return 
		 */
		public function get q_acc_show(): String{
			return _q_acc_show;
		}
		
		/**
		 * set 接受时显示npc
		 */
		public function set q_acc_show(value: String): void{
			this._q_acc_show = value;
		}
		
		/**
		 * get 接受时隐藏npc
		 * @return 
		 */
		public function get q_acc_hide(): String{
			return _q_acc_hide;
		}
		
		/**
		 * set 接受时隐藏npc
		 */
		public function set q_acc_hide(value: String): void{
			this._q_acc_hide = value;
		}
		
		/**
		 * get 完成时显示npc
		 * @return 
		 */
		public function get q_end_show(): String{
			return _q_end_show;
		}
		
		/**
		 * set 完成时显示npc
		 */
		public function set q_end_show(value: String): void{
			this._q_end_show = value;
		}
		
		/**
		 * get 完成时隐藏npc
		 * @return 
		 */
		public function get q_end_hide(): String{
			return _q_end_hide;
		}
		
		/**
		 * set 完成时隐藏npc
		 */
		public function set q_end_hide(value: String): void{
			this._q_end_hide = value;
		}
		
		/**
		 * get 是否需要完成动作（1是，非1即为否）
		 * @return 
		 */
		public function get q_end_needaction(): int{
			return _q_end_needaction;
		}
		
		/**
		 * set 是否需要完成动作（1是，非1即为否）
		 */
		public function set q_end_needaction(value: int): void{
			this._q_end_needaction = value;
		}
		
		/**
		 * get 奖励脚本
		 * @return 
		 */
		public function get q_rewards_scrpt(): int{
			return _q_rewards_scrpt;
		}
		
		/**
		 * set 奖励脚本
		 */
		public function set q_rewards_scrpt(value: int): void{
			this._q_rewards_scrpt = value;
		}
		
		/**
		 * get 达成条件后显示
		 * @return 
		 */
		public function get q_accomplish_show(): String{
			return _q_accomplish_show;
		}
		
		/**
		 * set 达成条件后显示
		 */
		public function set q_accomplish_show(value: String): void{
			this._q_accomplish_show = value;
		}
		
		/**
		 * get 达成条件后隐藏
		 * @return 
		 */
		public function get q_accomplish_hide(): String{
			return _q_accomplish_hide;
		}
		
		/**
		 * set 达成条件后隐藏
		 */
		public function set q_accomplish_hide(value: String): void{
			this._q_accomplish_hide = value;
		}
		
		/**
		 * get 采集目标NPC编号
		 * @return 
		 */
		public function get q_collection_target(): int{
			return _q_collection_target;
		}
		
		/**
		 * set 采集目标NPC编号
		 */
		public function set q_collection_target(value: int): void{
			this._q_collection_target = value;
		}
		
		/**
		 * get 完成任务显示怪物
		 * @return 
		 */
		public function get q_end_show_moster(): String{
			return _q_end_show_moster;
		}
		
		/**
		 * set 完成任务显示怪物
		 */
		public function set q_end_show_moster(value: String): void{
			this._q_end_show_moster = value;
		}
		
	}
}