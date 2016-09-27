package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_petinfo
	 */
	public class Q_petinfo extends Bean{
	
		//侍宠ID
		private var _q_model_id: int;
		
		//侍宠名称
		private var _q_name: String;
		
		//显示需要人物等级
		private var _q_level: int;
		
		//钦点军功条件
		private var _q_rank_cond: int;
		
		//钦点需要已获得侍宠
		private var _q_got_needpet: int;
		
		//钦点需要人物等级
		private var _q_got_needlevel: int;
		
		//钦点需要完成任务
		private var _q_got_need_task: int;
		
		//钦点消耗材料编号_数量;材料编号_数量
		private var _q_got_resumeitem: String;
		
		//钦点消耗材料数量
		private var _q_got_resumenum: int;
		
		//钦点消耗声望
		private var _q_got_resume_sw: int;
		
		//钦点消耗铜币
		private var _q_got_resume_gold: int;
		
		//钦点成功是否公告（0不公告，1公告）
		private var _q_got_notice: int;
		
		//侍宠可升级至最大等级
		private var _q_pet_maxlevel: int;
		
		//侍宠场景造型资源编号
		private var _q_res_scene_id: String;
		
		//侍宠展示面板造型资源编号
		private var _q_res_panel_id: String;
		
		//情诗资源编号
		private var _q_res_poetry_id: int;
		
		//头像资源编号
		private var _q_res_head_id: int;
		
		//攻击时音效编号
		private var _q_sound_attack_id: int;
		
		//被攻击时音效编号
		private var _q_sound_defence_id: int;
		
		//死亡时音效编号
		private var _q_sound_die_id: int;
		
		//普通合体增加属性次数
		private var _q_ht_count: int;
		
		//每日合体次数
		private var _q_ht_daycount: int;
		
		//首次合体增加攻击
		private var _q_ht_firstattack: int;
		
		//首次合体增加防御
		private var _q_ht_firstdefence: int;
		
		//首次合体增加暴击
		private var _q_ht_firstcrit: int;
		
		//首次合体增加闪避
		private var _q_ht_firstdodge: int;
		
		//首次合体增加生命
		private var _q_ht_firsthp: int;
		
		//首次合体增加内力
		private var _q_ht_firstmp: int;
		
		//普通合体增加攻击
		private var _q_ht_addattack: int;
		
		//普通合体增加防御
		private var _q_ht_adddefence: int;
		
		//普通合体增加暴击
		private var _q_ht_addcrit: int;
		
		//普通合体增加闪避
		private var _q_ht_adddodge: int;
		
		//普通合体增加生命
		private var _q_ht_addhp: int;
		
		//普通合体增加内力
		private var _q_ht_addmp: int;
		
		//首次合体增加经验脚本编号
		private var _q_ht_first_exp_script: int;
		
		//普通合体增加经验脚本编号
		private var _q_ht_exp_script: int;
		
		//合体消耗游戏铜币
		private var _q_ht_resume_copper: int;
		
		//合体消耗材料编号
		private var _q_ht_resume_item: int;
		
		//合体消耗材料数量
		private var _q_ht_resume_num: int;
		
		//合体冷却时长（毫秒）
		private var _q_ht_cooldown: int;
		
		//清除冷却时间时单位时间（1分钟）消耗元宝数量
		private var _q_ht_clearcd_gold: int;
		
		//被攻击固定少血
		private var _q_fiexd_value: int;
		
		//是否可以切换技能(1可以切换，0不能切换)
		private var _q_iscgs: int;
		
		//切换技能所需VIP等级
		private var _q_cgs_need_viplevel: int;
		
		//侍宠主动技能ID（单攻）
		private var _q_skill_single: int;
		
		//侍宠主动技能ID（群攻）
		private var _q_skill_multi: int;
		
		//侍宠天赋技能
		private var _q_skill: int;
		
		//可切换技能
		private var _q_ablecg_skill: String;
		
		//携带技能格数
		private var _q_skill_num: int;
		
		//侍宠重生时间(毫秒)
		private var _q_revive_time: int;
		
		//侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
		private var _q_chat_ai: String;
		
		//侍宠品质
		private var _q_quality: int;
		
		//每日亲热次数
		private var _q_qr_daycount: int;
		
		//普通亲热增加攻击
		private var _q_qr_addattack: int;
		
		//普通亲热增加防御
		private var _q_qr_adddefence: int;
		
		//普通亲热增加暴击
		private var _q_qr_addcrit: int;
		
		//普通亲热增加闪避
		private var _q_qr_adddodge: int;
		
		//普通亲热增加生命
		private var _q_qr_addhp: int;
		
		//普通亲热增加内力
		private var _q_qr_firstmp: int;
		
		//首次亲热增加经验脚本编号
		private var _q_firstqr_exp_script: int;
		
		//普通亲热增加经验脚本编号
		private var _q_qr_exp_script: int;
		
		//亲热消耗真气
		private var _q_qr_resume_zhenqi: int;
		
		//亲热消耗游戏铜币
		private var _q_qr_resume_gold: int;
		
		//亲热消耗材料编号
		private var _q_qr_resumeitem: String;
		
		//亲热冷却时长（毫秒）
		private var _q_qr_cd: int;
		
		//最大亲热次数
		private var _q_qr_countmax: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//侍宠ID
			_q_model_id = readInt();
			//侍宠名称
			_q_name = readString();
			//显示需要人物等级
			_q_level = readInt();
			//钦点军功条件
			_q_rank_cond = readInt();
			//钦点需要已获得侍宠
			_q_got_needpet = readInt();
			//钦点需要人物等级
			_q_got_needlevel = readInt();
			//钦点需要完成任务
			_q_got_need_task = readInt();
			//钦点消耗材料编号_数量;材料编号_数量
			_q_got_resumeitem = readString();
			//钦点消耗材料数量
			_q_got_resumenum = readInt();
			//钦点消耗声望
			_q_got_resume_sw = readInt();
			//钦点消耗铜币
			_q_got_resume_gold = readInt();
			//钦点成功是否公告（0不公告，1公告）
			_q_got_notice = readInt();
			//侍宠可升级至最大等级
			_q_pet_maxlevel = readInt();
			//侍宠场景造型资源编号
			_q_res_scene_id = readString();
			//侍宠展示面板造型资源编号
			_q_res_panel_id = readString();
			//情诗资源编号
			_q_res_poetry_id = readInt();
			//头像资源编号
			_q_res_head_id = readInt();
			//攻击时音效编号
			_q_sound_attack_id = readInt();
			//被攻击时音效编号
			_q_sound_defence_id = readInt();
			//死亡时音效编号
			_q_sound_die_id = readInt();
			//普通合体增加属性次数
			_q_ht_count = readInt();
			//每日合体次数
			_q_ht_daycount = readInt();
			//首次合体增加攻击
			_q_ht_firstattack = readInt();
			//首次合体增加防御
			_q_ht_firstdefence = readInt();
			//首次合体增加暴击
			_q_ht_firstcrit = readInt();
			//首次合体增加闪避
			_q_ht_firstdodge = readInt();
			//首次合体增加生命
			_q_ht_firsthp = readInt();
			//首次合体增加内力
			_q_ht_firstmp = readInt();
			//普通合体增加攻击
			_q_ht_addattack = readInt();
			//普通合体增加防御
			_q_ht_adddefence = readInt();
			//普通合体增加暴击
			_q_ht_addcrit = readInt();
			//普通合体增加闪避
			_q_ht_adddodge = readInt();
			//普通合体增加生命
			_q_ht_addhp = readInt();
			//普通合体增加内力
			_q_ht_addmp = readInt();
			//首次合体增加经验脚本编号
			_q_ht_first_exp_script = readInt();
			//普通合体增加经验脚本编号
			_q_ht_exp_script = readInt();
			//合体消耗游戏铜币
			_q_ht_resume_copper = readInt();
			//合体消耗材料编号
			_q_ht_resume_item = readInt();
			//合体消耗材料数量
			_q_ht_resume_num = readInt();
			//合体冷却时长（毫秒）
			_q_ht_cooldown = readInt();
			//清除冷却时间时单位时间（1分钟）消耗元宝数量
			_q_ht_clearcd_gold = readInt();
			//被攻击固定少血
			_q_fiexd_value = readInt();
			//是否可以切换技能(1可以切换，0不能切换)
			_q_iscgs = readInt();
			//切换技能所需VIP等级
			_q_cgs_need_viplevel = readInt();
			//侍宠主动技能ID（单攻）
			_q_skill_single = readInt();
			//侍宠主动技能ID（群攻）
			_q_skill_multi = readInt();
			//侍宠天赋技能
			_q_skill = readInt();
			//可切换技能
			_q_ablecg_skill = readString();
			//携带技能格数
			_q_skill_num = readInt();
			//侍宠重生时间(毫秒)
			_q_revive_time = readInt();
			//侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
			_q_chat_ai = readString();
			//侍宠品质
			_q_quality = readInt();
			//每日亲热次数
			_q_qr_daycount = readInt();
			//普通亲热增加攻击
			_q_qr_addattack = readInt();
			//普通亲热增加防御
			_q_qr_adddefence = readInt();
			//普通亲热增加暴击
			_q_qr_addcrit = readInt();
			//普通亲热增加闪避
			_q_qr_adddodge = readInt();
			//普通亲热增加生命
			_q_qr_addhp = readInt();
			//普通亲热增加内力
			_q_qr_firstmp = readInt();
			//首次亲热增加经验脚本编号
			_q_firstqr_exp_script = readInt();
			//普通亲热增加经验脚本编号
			_q_qr_exp_script = readInt();
			//亲热消耗真气
			_q_qr_resume_zhenqi = readInt();
			//亲热消耗游戏铜币
			_q_qr_resume_gold = readInt();
			//亲热消耗材料编号
			_q_qr_resumeitem = readString();
			//亲热冷却时长（毫秒）
			_q_qr_cd = readInt();
			//最大亲热次数
			_q_qr_countmax = readInt();
			return true;
		}
		
		/**
		 * get 侍宠ID
		 * @return 
		 */
		public function get q_model_id(): int{
			return _q_model_id;
		}
		
		/**
		 * set 侍宠ID
		 */
		public function set q_model_id(value: int): void{
			this._q_model_id = value;
		}
		
		/**
		 * get 侍宠名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 侍宠名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 显示需要人物等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 显示需要人物等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 钦点军功条件
		 * @return 
		 */
		public function get q_rank_cond(): int{
			return _q_rank_cond;
		}
		
		/**
		 * set 钦点军功条件
		 */
		public function set q_rank_cond(value: int): void{
			this._q_rank_cond = value;
		}
		
		/**
		 * get 钦点需要已获得侍宠
		 * @return 
		 */
		public function get q_got_needpet(): int{
			return _q_got_needpet;
		}
		
		/**
		 * set 钦点需要已获得侍宠
		 */
		public function set q_got_needpet(value: int): void{
			this._q_got_needpet = value;
		}
		
		/**
		 * get 钦点需要人物等级
		 * @return 
		 */
		public function get q_got_needlevel(): int{
			return _q_got_needlevel;
		}
		
		/**
		 * set 钦点需要人物等级
		 */
		public function set q_got_needlevel(value: int): void{
			this._q_got_needlevel = value;
		}
		
		/**
		 * get 钦点需要完成任务
		 * @return 
		 */
		public function get q_got_need_task(): int{
			return _q_got_need_task;
		}
		
		/**
		 * set 钦点需要完成任务
		 */
		public function set q_got_need_task(value: int): void{
			this._q_got_need_task = value;
		}
		
		/**
		 * get 钦点消耗材料编号_数量;材料编号_数量
		 * @return 
		 */
		public function get q_got_resumeitem(): String{
			return _q_got_resumeitem;
		}
		
		/**
		 * set 钦点消耗材料编号_数量;材料编号_数量
		 */
		public function set q_got_resumeitem(value: String): void{
			this._q_got_resumeitem = value;
		}
		
		/**
		 * get 钦点消耗材料数量
		 * @return 
		 */
		public function get q_got_resumenum(): int{
			return _q_got_resumenum;
		}
		
		/**
		 * set 钦点消耗材料数量
		 */
		public function set q_got_resumenum(value: int): void{
			this._q_got_resumenum = value;
		}
		
		/**
		 * get 钦点消耗声望
		 * @return 
		 */
		public function get q_got_resume_sw(): int{
			return _q_got_resume_sw;
		}
		
		/**
		 * set 钦点消耗声望
		 */
		public function set q_got_resume_sw(value: int): void{
			this._q_got_resume_sw = value;
		}
		
		/**
		 * get 钦点消耗铜币
		 * @return 
		 */
		public function get q_got_resume_gold(): int{
			return _q_got_resume_gold;
		}
		
		/**
		 * set 钦点消耗铜币
		 */
		public function set q_got_resume_gold(value: int): void{
			this._q_got_resume_gold = value;
		}
		
		/**
		 * get 钦点成功是否公告（0不公告，1公告）
		 * @return 
		 */
		public function get q_got_notice(): int{
			return _q_got_notice;
		}
		
		/**
		 * set 钦点成功是否公告（0不公告，1公告）
		 */
		public function set q_got_notice(value: int): void{
			this._q_got_notice = value;
		}
		
		/**
		 * get 侍宠可升级至最大等级
		 * @return 
		 */
		public function get q_pet_maxlevel(): int{
			return _q_pet_maxlevel;
		}
		
		/**
		 * set 侍宠可升级至最大等级
		 */
		public function set q_pet_maxlevel(value: int): void{
			this._q_pet_maxlevel = value;
		}
		
		/**
		 * get 侍宠场景造型资源编号
		 * @return 
		 */
		public function get q_res_scene_id(): String{
			return _q_res_scene_id;
		}
		
		/**
		 * set 侍宠场景造型资源编号
		 */
		public function set q_res_scene_id(value: String): void{
			this._q_res_scene_id = value;
		}
		
		/**
		 * get 侍宠展示面板造型资源编号
		 * @return 
		 */
		public function get q_res_panel_id(): String{
			return _q_res_panel_id;
		}
		
		/**
		 * set 侍宠展示面板造型资源编号
		 */
		public function set q_res_panel_id(value: String): void{
			this._q_res_panel_id = value;
		}
		
		/**
		 * get 情诗资源编号
		 * @return 
		 */
		public function get q_res_poetry_id(): int{
			return _q_res_poetry_id;
		}
		
		/**
		 * set 情诗资源编号
		 */
		public function set q_res_poetry_id(value: int): void{
			this._q_res_poetry_id = value;
		}
		
		/**
		 * get 头像资源编号
		 * @return 
		 */
		public function get q_res_head_id(): int{
			return _q_res_head_id;
		}
		
		/**
		 * set 头像资源编号
		 */
		public function set q_res_head_id(value: int): void{
			this._q_res_head_id = value;
		}
		
		/**
		 * get 攻击时音效编号
		 * @return 
		 */
		public function get q_sound_attack_id(): int{
			return _q_sound_attack_id;
		}
		
		/**
		 * set 攻击时音效编号
		 */
		public function set q_sound_attack_id(value: int): void{
			this._q_sound_attack_id = value;
		}
		
		/**
		 * get 被攻击时音效编号
		 * @return 
		 */
		public function get q_sound_defence_id(): int{
			return _q_sound_defence_id;
		}
		
		/**
		 * set 被攻击时音效编号
		 */
		public function set q_sound_defence_id(value: int): void{
			this._q_sound_defence_id = value;
		}
		
		/**
		 * get 死亡时音效编号
		 * @return 
		 */
		public function get q_sound_die_id(): int{
			return _q_sound_die_id;
		}
		
		/**
		 * set 死亡时音效编号
		 */
		public function set q_sound_die_id(value: int): void{
			this._q_sound_die_id = value;
		}
		
		/**
		 * get 普通合体增加属性次数
		 * @return 
		 */
		public function get q_ht_count(): int{
			return _q_ht_count;
		}
		
		/**
		 * set 普通合体增加属性次数
		 */
		public function set q_ht_count(value: int): void{
			this._q_ht_count = value;
		}
		
		/**
		 * get 每日合体次数
		 * @return 
		 */
		public function get q_ht_daycount(): int{
			return _q_ht_daycount;
		}
		
		/**
		 * set 每日合体次数
		 */
		public function set q_ht_daycount(value: int): void{
			this._q_ht_daycount = value;
		}
		
		/**
		 * get 首次合体增加攻击
		 * @return 
		 */
		public function get q_ht_firstattack(): int{
			return _q_ht_firstattack;
		}
		
		/**
		 * set 首次合体增加攻击
		 */
		public function set q_ht_firstattack(value: int): void{
			this._q_ht_firstattack = value;
		}
		
		/**
		 * get 首次合体增加防御
		 * @return 
		 */
		public function get q_ht_firstdefence(): int{
			return _q_ht_firstdefence;
		}
		
		/**
		 * set 首次合体增加防御
		 */
		public function set q_ht_firstdefence(value: int): void{
			this._q_ht_firstdefence = value;
		}
		
		/**
		 * get 首次合体增加暴击
		 * @return 
		 */
		public function get q_ht_firstcrit(): int{
			return _q_ht_firstcrit;
		}
		
		/**
		 * set 首次合体增加暴击
		 */
		public function set q_ht_firstcrit(value: int): void{
			this._q_ht_firstcrit = value;
		}
		
		/**
		 * get 首次合体增加闪避
		 * @return 
		 */
		public function get q_ht_firstdodge(): int{
			return _q_ht_firstdodge;
		}
		
		/**
		 * set 首次合体增加闪避
		 */
		public function set q_ht_firstdodge(value: int): void{
			this._q_ht_firstdodge = value;
		}
		
		/**
		 * get 首次合体增加生命
		 * @return 
		 */
		public function get q_ht_firsthp(): int{
			return _q_ht_firsthp;
		}
		
		/**
		 * set 首次合体增加生命
		 */
		public function set q_ht_firsthp(value: int): void{
			this._q_ht_firsthp = value;
		}
		
		/**
		 * get 首次合体增加内力
		 * @return 
		 */
		public function get q_ht_firstmp(): int{
			return _q_ht_firstmp;
		}
		
		/**
		 * set 首次合体增加内力
		 */
		public function set q_ht_firstmp(value: int): void{
			this._q_ht_firstmp = value;
		}
		
		/**
		 * get 普通合体增加攻击
		 * @return 
		 */
		public function get q_ht_addattack(): int{
			return _q_ht_addattack;
		}
		
		/**
		 * set 普通合体增加攻击
		 */
		public function set q_ht_addattack(value: int): void{
			this._q_ht_addattack = value;
		}
		
		/**
		 * get 普通合体增加防御
		 * @return 
		 */
		public function get q_ht_adddefence(): int{
			return _q_ht_adddefence;
		}
		
		/**
		 * set 普通合体增加防御
		 */
		public function set q_ht_adddefence(value: int): void{
			this._q_ht_adddefence = value;
		}
		
		/**
		 * get 普通合体增加暴击
		 * @return 
		 */
		public function get q_ht_addcrit(): int{
			return _q_ht_addcrit;
		}
		
		/**
		 * set 普通合体增加暴击
		 */
		public function set q_ht_addcrit(value: int): void{
			this._q_ht_addcrit = value;
		}
		
		/**
		 * get 普通合体增加闪避
		 * @return 
		 */
		public function get q_ht_adddodge(): int{
			return _q_ht_adddodge;
		}
		
		/**
		 * set 普通合体增加闪避
		 */
		public function set q_ht_adddodge(value: int): void{
			this._q_ht_adddodge = value;
		}
		
		/**
		 * get 普通合体增加生命
		 * @return 
		 */
		public function get q_ht_addhp(): int{
			return _q_ht_addhp;
		}
		
		/**
		 * set 普通合体增加生命
		 */
		public function set q_ht_addhp(value: int): void{
			this._q_ht_addhp = value;
		}
		
		/**
		 * get 普通合体增加内力
		 * @return 
		 */
		public function get q_ht_addmp(): int{
			return _q_ht_addmp;
		}
		
		/**
		 * set 普通合体增加内力
		 */
		public function set q_ht_addmp(value: int): void{
			this._q_ht_addmp = value;
		}
		
		/**
		 * get 首次合体增加经验脚本编号
		 * @return 
		 */
		public function get q_ht_first_exp_script(): int{
			return _q_ht_first_exp_script;
		}
		
		/**
		 * set 首次合体增加经验脚本编号
		 */
		public function set q_ht_first_exp_script(value: int): void{
			this._q_ht_first_exp_script = value;
		}
		
		/**
		 * get 普通合体增加经验脚本编号
		 * @return 
		 */
		public function get q_ht_exp_script(): int{
			return _q_ht_exp_script;
		}
		
		/**
		 * set 普通合体增加经验脚本编号
		 */
		public function set q_ht_exp_script(value: int): void{
			this._q_ht_exp_script = value;
		}
		
		/**
		 * get 合体消耗游戏铜币
		 * @return 
		 */
		public function get q_ht_resume_copper(): int{
			return _q_ht_resume_copper;
		}
		
		/**
		 * set 合体消耗游戏铜币
		 */
		public function set q_ht_resume_copper(value: int): void{
			this._q_ht_resume_copper = value;
		}
		
		/**
		 * get 合体消耗材料编号
		 * @return 
		 */
		public function get q_ht_resume_item(): int{
			return _q_ht_resume_item;
		}
		
		/**
		 * set 合体消耗材料编号
		 */
		public function set q_ht_resume_item(value: int): void{
			this._q_ht_resume_item = value;
		}
		
		/**
		 * get 合体消耗材料数量
		 * @return 
		 */
		public function get q_ht_resume_num(): int{
			return _q_ht_resume_num;
		}
		
		/**
		 * set 合体消耗材料数量
		 */
		public function set q_ht_resume_num(value: int): void{
			this._q_ht_resume_num = value;
		}
		
		/**
		 * get 合体冷却时长（毫秒）
		 * @return 
		 */
		public function get q_ht_cooldown(): int{
			return _q_ht_cooldown;
		}
		
		/**
		 * set 合体冷却时长（毫秒）
		 */
		public function set q_ht_cooldown(value: int): void{
			this._q_ht_cooldown = value;
		}
		
		/**
		 * get 清除冷却时间时单位时间（1分钟）消耗元宝数量
		 * @return 
		 */
		public function get q_ht_clearcd_gold(): int{
			return _q_ht_clearcd_gold;
		}
		
		/**
		 * set 清除冷却时间时单位时间（1分钟）消耗元宝数量
		 */
		public function set q_ht_clearcd_gold(value: int): void{
			this._q_ht_clearcd_gold = value;
		}
		
		/**
		 * get 被攻击固定少血
		 * @return 
		 */
		public function get q_fiexd_value(): int{
			return _q_fiexd_value;
		}
		
		/**
		 * set 被攻击固定少血
		 */
		public function set q_fiexd_value(value: int): void{
			this._q_fiexd_value = value;
		}
		
		/**
		 * get 是否可以切换技能(1可以切换，0不能切换)
		 * @return 
		 */
		public function get q_iscgs(): int{
			return _q_iscgs;
		}
		
		/**
		 * set 是否可以切换技能(1可以切换，0不能切换)
		 */
		public function set q_iscgs(value: int): void{
			this._q_iscgs = value;
		}
		
		/**
		 * get 切换技能所需VIP等级
		 * @return 
		 */
		public function get q_cgs_need_viplevel(): int{
			return _q_cgs_need_viplevel;
		}
		
		/**
		 * set 切换技能所需VIP等级
		 */
		public function set q_cgs_need_viplevel(value: int): void{
			this._q_cgs_need_viplevel = value;
		}
		
		/**
		 * get 侍宠主动技能ID（单攻）
		 * @return 
		 */
		public function get q_skill_single(): int{
			return _q_skill_single;
		}
		
		/**
		 * set 侍宠主动技能ID（单攻）
		 */
		public function set q_skill_single(value: int): void{
			this._q_skill_single = value;
		}
		
		/**
		 * get 侍宠主动技能ID（群攻）
		 * @return 
		 */
		public function get q_skill_multi(): int{
			return _q_skill_multi;
		}
		
		/**
		 * set 侍宠主动技能ID（群攻）
		 */
		public function set q_skill_multi(value: int): void{
			this._q_skill_multi = value;
		}
		
		/**
		 * get 侍宠天赋技能
		 * @return 
		 */
		public function get q_skill(): int{
			return _q_skill;
		}
		
		/**
		 * set 侍宠天赋技能
		 */
		public function set q_skill(value: int): void{
			this._q_skill = value;
		}
		
		/**
		 * get 可切换技能
		 * @return 
		 */
		public function get q_ablecg_skill(): String{
			return _q_ablecg_skill;
		}
		
		/**
		 * set 可切换技能
		 */
		public function set q_ablecg_skill(value: String): void{
			this._q_ablecg_skill = value;
		}
		
		/**
		 * get 携带技能格数
		 * @return 
		 */
		public function get q_skill_num(): int{
			return _q_skill_num;
		}
		
		/**
		 * set 携带技能格数
		 */
		public function set q_skill_num(value: int): void{
			this._q_skill_num = value;
		}
		
		/**
		 * get 侍宠重生时间(毫秒)
		 * @return 
		 */
		public function get q_revive_time(): int{
			return _q_revive_time;
		}
		
		/**
		 * set 侍宠重生时间(毫秒)
		 */
		public function set q_revive_time(value: int): void{
			this._q_revive_time = value;
		}
		
		/**
		 * get 侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
		 * @return 
		 */
		public function get q_chat_ai(): String{
			return _q_chat_ai;
		}
		
		/**
		 * set 侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
		 */
		public function set q_chat_ai(value: String): void{
			this._q_chat_ai = value;
		}
		
		/**
		 * get 侍宠品质
		 * @return 
		 */
		public function get q_quality(): int{
			return _q_quality;
		}
		
		/**
		 * set 侍宠品质
		 */
		public function set q_quality(value: int): void{
			this._q_quality = value;
		}
		
		/**
		 * get 每日亲热次数
		 * @return 
		 */
		public function get q_qr_daycount(): int{
			return _q_qr_daycount;
		}
		
		/**
		 * set 每日亲热次数
		 */
		public function set q_qr_daycount(value: int): void{
			this._q_qr_daycount = value;
		}
		
		/**
		 * get 普通亲热增加攻击
		 * @return 
		 */
		public function get q_qr_addattack(): int{
			return _q_qr_addattack;
		}
		
		/**
		 * set 普通亲热增加攻击
		 */
		public function set q_qr_addattack(value: int): void{
			this._q_qr_addattack = value;
		}
		
		/**
		 * get 普通亲热增加防御
		 * @return 
		 */
		public function get q_qr_adddefence(): int{
			return _q_qr_adddefence;
		}
		
		/**
		 * set 普通亲热增加防御
		 */
		public function set q_qr_adddefence(value: int): void{
			this._q_qr_adddefence = value;
		}
		
		/**
		 * get 普通亲热增加暴击
		 * @return 
		 */
		public function get q_qr_addcrit(): int{
			return _q_qr_addcrit;
		}
		
		/**
		 * set 普通亲热增加暴击
		 */
		public function set q_qr_addcrit(value: int): void{
			this._q_qr_addcrit = value;
		}
		
		/**
		 * get 普通亲热增加闪避
		 * @return 
		 */
		public function get q_qr_adddodge(): int{
			return _q_qr_adddodge;
		}
		
		/**
		 * set 普通亲热增加闪避
		 */
		public function set q_qr_adddodge(value: int): void{
			this._q_qr_adddodge = value;
		}
		
		/**
		 * get 普通亲热增加生命
		 * @return 
		 */
		public function get q_qr_addhp(): int{
			return _q_qr_addhp;
		}
		
		/**
		 * set 普通亲热增加生命
		 */
		public function set q_qr_addhp(value: int): void{
			this._q_qr_addhp = value;
		}
		
		/**
		 * get 普通亲热增加内力
		 * @return 
		 */
		public function get q_qr_firstmp(): int{
			return _q_qr_firstmp;
		}
		
		/**
		 * set 普通亲热增加内力
		 */
		public function set q_qr_firstmp(value: int): void{
			this._q_qr_firstmp = value;
		}
		
		/**
		 * get 首次亲热增加经验脚本编号
		 * @return 
		 */
		public function get q_firstqr_exp_script(): int{
			return _q_firstqr_exp_script;
		}
		
		/**
		 * set 首次亲热增加经验脚本编号
		 */
		public function set q_firstqr_exp_script(value: int): void{
			this._q_firstqr_exp_script = value;
		}
		
		/**
		 * get 普通亲热增加经验脚本编号
		 * @return 
		 */
		public function get q_qr_exp_script(): int{
			return _q_qr_exp_script;
		}
		
		/**
		 * set 普通亲热增加经验脚本编号
		 */
		public function set q_qr_exp_script(value: int): void{
			this._q_qr_exp_script = value;
		}
		
		/**
		 * get 亲热消耗真气
		 * @return 
		 */
		public function get q_qr_resume_zhenqi(): int{
			return _q_qr_resume_zhenqi;
		}
		
		/**
		 * set 亲热消耗真气
		 */
		public function set q_qr_resume_zhenqi(value: int): void{
			this._q_qr_resume_zhenqi = value;
		}
		
		/**
		 * get 亲热消耗游戏铜币
		 * @return 
		 */
		public function get q_qr_resume_gold(): int{
			return _q_qr_resume_gold;
		}
		
		/**
		 * set 亲热消耗游戏铜币
		 */
		public function set q_qr_resume_gold(value: int): void{
			this._q_qr_resume_gold = value;
		}
		
		/**
		 * get 亲热消耗材料编号
		 * @return 
		 */
		public function get q_qr_resumeitem(): String{
			return _q_qr_resumeitem;
		}
		
		/**
		 * set 亲热消耗材料编号
		 */
		public function set q_qr_resumeitem(value: String): void{
			this._q_qr_resumeitem = value;
		}
		
		/**
		 * get 亲热冷却时长（毫秒）
		 * @return 
		 */
		public function get q_qr_cd(): int{
			return _q_qr_cd;
		}
		
		/**
		 * set 亲热冷却时长（毫秒）
		 */
		public function set q_qr_cd(value: int): void{
			this._q_qr_cd = value;
		}
		
		/**
		 * get 最大亲热次数
		 * @return 
		 */
		public function get q_qr_countmax(): int{
			return _q_qr_countmax;
		}
		
		/**
		 * set 最大亲热次数
		 */
		public function set q_qr_countmax(value: int): void{
			this._q_qr_countmax = value;
		}
		
	}
}