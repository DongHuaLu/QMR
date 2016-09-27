package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_skill_model
	 */
	public class Q_skill_model extends Bean{
	
		//技能编号_技能等级
		private var _q_skillID_q_grade: String;
		
		//技能编号
		private var _q_skillID: int;
		
		//技能名称
		private var _q_skillName: String;
		
		//所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
		private var _q_panel_type: int;
		
		//位置编号（0不在武功面板中）
		private var _q_index: int;
		
		//显示所需人物等级
		private var _q_show_needgrade: int;
		
		//武功面板上的简单描述（需支持加色，换行，加粗Html语法）
		private var _q_desc: String;
		
		//武功面板上显示的SWF动画
		private var _q_swf: String;
		
		//鼠标TIPS界面描述信息（支持Html语法）
		private var _q_tips: String;
		
		//学习所需人物等级
		private var _q_study_needgrade: int;
		
		//是否默认学会（1默认学会，0不学会）
		private var _q_default_study: int;
		
		//学习所需技能书编号
		private var _q_study_needbook: int;
		
		//技能书出处描述信息
		private var _q_book_desc: String;
		
		//使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
		private var _q_skill_user: int;
		
		//使用方式（1主动技能，2被动技能）
		private var _q_trigger_type: int;
		
		//使用距离限制（自身与目标之间的距离）（单位：格数）
		private var _q_range_limit: int;
		
		//触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
		private var _q_passive_action: int;
		
		//被动触发几率（本处填万分比的分子）
		private var _q_passive_prob: int;
		
		//作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
		private var _q_target: int;
		
		//作用范围形状（1单体，2矩形，3扇形，4圆形）
		private var _q_area_shape: int;
		
		//作用范围中心点（1自身为中心，2目标为中心）
		private var _q_area_target: int;
		
		//矩形长（像素）
		private var _q_area_length: int;
		
		//矩形宽
		private var _q_area_width: int;
		
		//扇形开始角度
		private var _q_sector_start: int;
		
		//扇形结束角度
		private var _q_secto_end: int;
		
		//扇形半径
		private var _q_sector_radius: int;
		
		//圆半径
		private var _q_circular_radius: int;
		
		//作用人数上限
		private var _q_target_max: int;
		
		//是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
		private var _q_default_enable: int;
		
		//是否可以注册快捷栏（1可以，2不可以）
		private var _q_shortcut: int;
		
		//冷却时间
		private var _q_cd: int;
		
		//公共冷却时间
		private var _q_public_cd: int;
		
		//公共冷却层级
		private var _q_public_cd_level: int;
		
		//该技能的克制技能编号
		private var _q_restriction: int;
		
		//是否触发一次战斗公式的伤害（0不触发，1触发）
		private var _q_trigger_figth_hurt: int;
		
		//伤害修正系数（万分比）
		private var _q_hurt_correct_factor: int;
		
		//技能等级
		private var _q_grade: int;
		
		//升级所需人物等级
		private var _q_needgrade: int;
		
		//升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
		private var _q_up_need_goods: String;
		
		//所需材料的出处说明（文字描述）
		private var _q_up_need_goods_desc: String;
		
		//升级所需铜币
		private var _q_up_need_copper: int;
		
		//升级所需真气
		private var _q_up_need_zhengqi: int;
		
		//真气来源出处说明（文字描述）
		private var _q_up_need_zhengqi_desc: String;
		
		//升级所需时间（单位：毫秒）
		private var _q_up_need_time: int;
		
		//升级成功几率
		private var _q_up_prob: int;
		
		//使用消耗内力值
		private var _q_need_mp: int;
		
		//每次造成怪物仇恨值
		private var _q_enmity: int;
		
		//技能加成攻击力值
		private var _q_attack_addition: int;
		
		//技能造成无视防御伤害值
		private var _q_ignore_defence: int;
		
		//使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
		private var _q_passive_buff: String;
		
		//成功施加BUFF系数
		private var _q_bufq_trigger_factor: int;
		
		//成功施加BUFF抵抗系数
		private var _q_bufq_defence_factor: int;
		
		//BUFF持续时间提升系数
		private var _q_bufq_timeup_factor: int;
		
		//BUFF持续时间减免系数
		private var _q_bufq_timedown_factor: int;
		
		//BUFF作用数值修正系数
		private var _q_bufq_num_factor: int;
		
		//BUFF作用比例修正系数
		private var _q_bufq_action_factor: int;
		
		//技能调用攻击动作编号
		private var _q_attack_id: int;
		
		//延迟命中时间（单位：毫秒）
		private var _q_delay_time: int;
		
		//弹道飞行速度（单位：像素/秒）
		private var _q_trajectory_speed: int;
		
		//技能施法特效编号
		private var _q_use_effect: String;
		
		//技能弹道特效编号
		private var _q_trajectory_effect: String;
		
		//技能命中特效编号
		private var _q_hit_effect: String;
		
		//技能持续特效编号
		private var _q_series_effect: String;
		
		//技能小图标编号（36*36）
		private var _q_small_ico: int;
		
		//技能施法音效
		private var _q_use_sound: String;
		
		//技能命中音效
		private var _q_hit_sound: String;
		
		//（前端使用）战斗力加成
		private var _q_fight_bonus: int;
		
		//最大等级
		private var _q_max_level: int;
		
		//跳跃是否可使用技能(1是，0否)
		private var _q_is_Jump_skill: int;
		
		//调用脚本编号
		private var _q_skill_script: int;
		
		//是否忽视（防御，闪避，跳跃）
		private var _q_is_ignore: int;
		
		//技能类型（0普通技能，1火墙类型）
		private var _q_skill_type: int;
		
		//技能持续时间（毫秒）
		private var _q_skill_time: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能编号_技能等级
			_q_skillID_q_grade = readString();
			//技能编号
			_q_skillID = readInt();
			//技能名称
			_q_skillName = readString();
			//所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
			_q_panel_type = readInt();
			//位置编号（0不在武功面板中）
			_q_index = readInt();
			//显示所需人物等级
			_q_show_needgrade = readInt();
			//武功面板上的简单描述（需支持加色，换行，加粗Html语法）
			_q_desc = readString();
			//武功面板上显示的SWF动画
			_q_swf = readString();
			//鼠标TIPS界面描述信息（支持Html语法）
			_q_tips = readString();
			//学习所需人物等级
			_q_study_needgrade = readInt();
			//是否默认学会（1默认学会，0不学会）
			_q_default_study = readInt();
			//学习所需技能书编号
			_q_study_needbook = readInt();
			//技能书出处描述信息
			_q_book_desc = readString();
			//使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
			_q_skill_user = readInt();
			//使用方式（1主动技能，2被动技能）
			_q_trigger_type = readInt();
			//使用距离限制（自身与目标之间的距离）（单位：格数）
			_q_range_limit = readInt();
			//触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
			_q_passive_action = readInt();
			//被动触发几率（本处填万分比的分子）
			_q_passive_prob = readInt();
			//作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
			_q_target = readInt();
			//作用范围形状（1单体，2矩形，3扇形，4圆形）
			_q_area_shape = readInt();
			//作用范围中心点（1自身为中心，2目标为中心）
			_q_area_target = readInt();
			//矩形长（像素）
			_q_area_length = readInt();
			//矩形宽
			_q_area_width = readInt();
			//扇形开始角度
			_q_sector_start = readInt();
			//扇形结束角度
			_q_secto_end = readInt();
			//扇形半径
			_q_sector_radius = readInt();
			//圆半径
			_q_circular_radius = readInt();
			//作用人数上限
			_q_target_max = readInt();
			//是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
			_q_default_enable = readInt();
			//是否可以注册快捷栏（1可以，2不可以）
			_q_shortcut = readInt();
			//冷却时间
			_q_cd = readInt();
			//公共冷却时间
			_q_public_cd = readInt();
			//公共冷却层级
			_q_public_cd_level = readInt();
			//该技能的克制技能编号
			_q_restriction = readInt();
			//是否触发一次战斗公式的伤害（0不触发，1触发）
			_q_trigger_figth_hurt = readInt();
			//伤害修正系数（万分比）
			_q_hurt_correct_factor = readInt();
			//技能等级
			_q_grade = readInt();
			//升级所需人物等级
			_q_needgrade = readInt();
			//升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
			_q_up_need_goods = readString();
			//所需材料的出处说明（文字描述）
			_q_up_need_goods_desc = readString();
			//升级所需铜币
			_q_up_need_copper = readInt();
			//升级所需真气
			_q_up_need_zhengqi = readInt();
			//真气来源出处说明（文字描述）
			_q_up_need_zhengqi_desc = readString();
			//升级所需时间（单位：毫秒）
			_q_up_need_time = readInt();
			//升级成功几率
			_q_up_prob = readInt();
			//使用消耗内力值
			_q_need_mp = readInt();
			//每次造成怪物仇恨值
			_q_enmity = readInt();
			//技能加成攻击力值
			_q_attack_addition = readInt();
			//技能造成无视防御伤害值
			_q_ignore_defence = readInt();
			//使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
			_q_passive_buff = readString();
			//成功施加BUFF系数
			_q_bufq_trigger_factor = readInt();
			//成功施加BUFF抵抗系数
			_q_bufq_defence_factor = readInt();
			//BUFF持续时间提升系数
			_q_bufq_timeup_factor = readInt();
			//BUFF持续时间减免系数
			_q_bufq_timedown_factor = readInt();
			//BUFF作用数值修正系数
			_q_bufq_num_factor = readInt();
			//BUFF作用比例修正系数
			_q_bufq_action_factor = readInt();
			//技能调用攻击动作编号
			_q_attack_id = readInt();
			//延迟命中时间（单位：毫秒）
			_q_delay_time = readInt();
			//弹道飞行速度（单位：像素/秒）
			_q_trajectory_speed = readInt();
			//技能施法特效编号
			_q_use_effect = readString();
			//技能弹道特效编号
			_q_trajectory_effect = readString();
			//技能命中特效编号
			_q_hit_effect = readString();
			//技能持续特效编号
			_q_series_effect = readString();
			//技能小图标编号（36*36）
			_q_small_ico = readInt();
			//技能施法音效
			_q_use_sound = readString();
			//技能命中音效
			_q_hit_sound = readString();
			//（前端使用）战斗力加成
			_q_fight_bonus = readInt();
			//最大等级
			_q_max_level = readInt();
			//跳跃是否可使用技能(1是，0否)
			_q_is_Jump_skill = readInt();
			//调用脚本编号
			_q_skill_script = readInt();
			//是否忽视（防御，闪避，跳跃）
			_q_is_ignore = readInt();
			//技能类型（0普通技能，1火墙类型）
			_q_skill_type = readInt();
			//技能持续时间（毫秒）
			_q_skill_time = readInt();
			return true;
		}
		
		/**
		 * get 技能编号_技能等级
		 * @return 
		 */
		public function get q_skillID_q_grade(): String{
			return _q_skillID_q_grade;
		}
		
		/**
		 * set 技能编号_技能等级
		 */
		public function set q_skillID_q_grade(value: String): void{
			this._q_skillID_q_grade = value;
		}
		
		/**
		 * get 技能编号
		 * @return 
		 */
		public function get q_skillID(): int{
			return _q_skillID;
		}
		
		/**
		 * set 技能编号
		 */
		public function set q_skillID(value: int): void{
			this._q_skillID = value;
		}
		
		/**
		 * get 技能名称
		 * @return 
		 */
		public function get q_skillName(): String{
			return _q_skillName;
		}
		
		/**
		 * set 技能名称
		 */
		public function set q_skillName(value: String): void{
			this._q_skillName = value;
		}
		
		/**
		 * get 所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
		 * @return 
		 */
		public function get q_panel_type(): int{
			return _q_panel_type;
		}
		
		/**
		 * set 所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
		 */
		public function set q_panel_type(value: int): void{
			this._q_panel_type = value;
		}
		
		/**
		 * get 位置编号（0不在武功面板中）
		 * @return 
		 */
		public function get q_index(): int{
			return _q_index;
		}
		
		/**
		 * set 位置编号（0不在武功面板中）
		 */
		public function set q_index(value: int): void{
			this._q_index = value;
		}
		
		/**
		 * get 显示所需人物等级
		 * @return 
		 */
		public function get q_show_needgrade(): int{
			return _q_show_needgrade;
		}
		
		/**
		 * set 显示所需人物等级
		 */
		public function set q_show_needgrade(value: int): void{
			this._q_show_needgrade = value;
		}
		
		/**
		 * get 武功面板上的简单描述（需支持加色，换行，加粗Html语法）
		 * @return 
		 */
		public function get q_desc(): String{
			return _q_desc;
		}
		
		/**
		 * set 武功面板上的简单描述（需支持加色，换行，加粗Html语法）
		 */
		public function set q_desc(value: String): void{
			this._q_desc = value;
		}
		
		/**
		 * get 武功面板上显示的SWF动画
		 * @return 
		 */
		public function get q_swf(): String{
			return _q_swf;
		}
		
		/**
		 * set 武功面板上显示的SWF动画
		 */
		public function set q_swf(value: String): void{
			this._q_swf = value;
		}
		
		/**
		 * get 鼠标TIPS界面描述信息（支持Html语法）
		 * @return 
		 */
		public function get q_tips(): String{
			return _q_tips;
		}
		
		/**
		 * set 鼠标TIPS界面描述信息（支持Html语法）
		 */
		public function set q_tips(value: String): void{
			this._q_tips = value;
		}
		
		/**
		 * get 学习所需人物等级
		 * @return 
		 */
		public function get q_study_needgrade(): int{
			return _q_study_needgrade;
		}
		
		/**
		 * set 学习所需人物等级
		 */
		public function set q_study_needgrade(value: int): void{
			this._q_study_needgrade = value;
		}
		
		/**
		 * get 是否默认学会（1默认学会，0不学会）
		 * @return 
		 */
		public function get q_default_study(): int{
			return _q_default_study;
		}
		
		/**
		 * set 是否默认学会（1默认学会，0不学会）
		 */
		public function set q_default_study(value: int): void{
			this._q_default_study = value;
		}
		
		/**
		 * get 学习所需技能书编号
		 * @return 
		 */
		public function get q_study_needbook(): int{
			return _q_study_needbook;
		}
		
		/**
		 * set 学习所需技能书编号
		 */
		public function set q_study_needbook(value: int): void{
			this._q_study_needbook = value;
		}
		
		/**
		 * get 技能书出处描述信息
		 * @return 
		 */
		public function get q_book_desc(): String{
			return _q_book_desc;
		}
		
		/**
		 * set 技能书出处描述信息
		 */
		public function set q_book_desc(value: String): void{
			this._q_book_desc = value;
		}
		
		/**
		 * get 使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
		 * @return 
		 */
		public function get q_skill_user(): int{
			return _q_skill_user;
		}
		
		/**
		 * set 使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
		 */
		public function set q_skill_user(value: int): void{
			this._q_skill_user = value;
		}
		
		/**
		 * get 使用方式（1主动技能，2被动技能）
		 * @return 
		 */
		public function get q_trigger_type(): int{
			return _q_trigger_type;
		}
		
		/**
		 * set 使用方式（1主动技能，2被动技能）
		 */
		public function set q_trigger_type(value: int): void{
			this._q_trigger_type = value;
		}
		
		/**
		 * get 使用距离限制（自身与目标之间的距离）（单位：格数）
		 * @return 
		 */
		public function get q_range_limit(): int{
			return _q_range_limit;
		}
		
		/**
		 * set 使用距离限制（自身与目标之间的距离）（单位：格数）
		 */
		public function set q_range_limit(value: int): void{
			this._q_range_limit = value;
		}
		
		/**
		 * get 触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
		 * @return 
		 */
		public function get q_passive_action(): int{
			return _q_passive_action;
		}
		
		/**
		 * set 触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
		 */
		public function set q_passive_action(value: int): void{
			this._q_passive_action = value;
		}
		
		/**
		 * get 被动触发几率（本处填万分比的分子）
		 * @return 
		 */
		public function get q_passive_prob(): int{
			return _q_passive_prob;
		}
		
		/**
		 * set 被动触发几率（本处填万分比的分子）
		 */
		public function set q_passive_prob(value: int): void{
			this._q_passive_prob = value;
		}
		
		/**
		 * get 作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
		 * @return 
		 */
		public function get q_target(): int{
			return _q_target;
		}
		
		/**
		 * set 作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
		 */
		public function set q_target(value: int): void{
			this._q_target = value;
		}
		
		/**
		 * get 作用范围形状（1单体，2矩形，3扇形，4圆形）
		 * @return 
		 */
		public function get q_area_shape(): int{
			return _q_area_shape;
		}
		
		/**
		 * set 作用范围形状（1单体，2矩形，3扇形，4圆形）
		 */
		public function set q_area_shape(value: int): void{
			this._q_area_shape = value;
		}
		
		/**
		 * get 作用范围中心点（1自身为中心，2目标为中心）
		 * @return 
		 */
		public function get q_area_target(): int{
			return _q_area_target;
		}
		
		/**
		 * set 作用范围中心点（1自身为中心，2目标为中心）
		 */
		public function set q_area_target(value: int): void{
			this._q_area_target = value;
		}
		
		/**
		 * get 矩形长（像素）
		 * @return 
		 */
		public function get q_area_length(): int{
			return _q_area_length;
		}
		
		/**
		 * set 矩形长（像素）
		 */
		public function set q_area_length(value: int): void{
			this._q_area_length = value;
		}
		
		/**
		 * get 矩形宽
		 * @return 
		 */
		public function get q_area_width(): int{
			return _q_area_width;
		}
		
		/**
		 * set 矩形宽
		 */
		public function set q_area_width(value: int): void{
			this._q_area_width = value;
		}
		
		/**
		 * get 扇形开始角度
		 * @return 
		 */
		public function get q_sector_start(): int{
			return _q_sector_start;
		}
		
		/**
		 * set 扇形开始角度
		 */
		public function set q_sector_start(value: int): void{
			this._q_sector_start = value;
		}
		
		/**
		 * get 扇形结束角度
		 * @return 
		 */
		public function get q_secto_end(): int{
			return _q_secto_end;
		}
		
		/**
		 * set 扇形结束角度
		 */
		public function set q_secto_end(value: int): void{
			this._q_secto_end = value;
		}
		
		/**
		 * get 扇形半径
		 * @return 
		 */
		public function get q_sector_radius(): int{
			return _q_sector_radius;
		}
		
		/**
		 * set 扇形半径
		 */
		public function set q_sector_radius(value: int): void{
			this._q_sector_radius = value;
		}
		
		/**
		 * get 圆半径
		 * @return 
		 */
		public function get q_circular_radius(): int{
			return _q_circular_radius;
		}
		
		/**
		 * set 圆半径
		 */
		public function set q_circular_radius(value: int): void{
			this._q_circular_radius = value;
		}
		
		/**
		 * get 作用人数上限
		 * @return 
		 */
		public function get q_target_max(): int{
			return _q_target_max;
		}
		
		/**
		 * set 作用人数上限
		 */
		public function set q_target_max(value: int): void{
			this._q_target_max = value;
		}
		
		/**
		 * get 是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
		 * @return 
		 */
		public function get q_default_enable(): int{
			return _q_default_enable;
		}
		
		/**
		 * set 是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
		 */
		public function set q_default_enable(value: int): void{
			this._q_default_enable = value;
		}
		
		/**
		 * get 是否可以注册快捷栏（1可以，2不可以）
		 * @return 
		 */
		public function get q_shortcut(): int{
			return _q_shortcut;
		}
		
		/**
		 * set 是否可以注册快捷栏（1可以，2不可以）
		 */
		public function set q_shortcut(value: int): void{
			this._q_shortcut = value;
		}
		
		/**
		 * get 冷却时间
		 * @return 
		 */
		public function get q_cd(): int{
			return _q_cd;
		}
		
		/**
		 * set 冷却时间
		 */
		public function set q_cd(value: int): void{
			this._q_cd = value;
		}
		
		/**
		 * get 公共冷却时间
		 * @return 
		 */
		public function get q_public_cd(): int{
			return _q_public_cd;
		}
		
		/**
		 * set 公共冷却时间
		 */
		public function set q_public_cd(value: int): void{
			this._q_public_cd = value;
		}
		
		/**
		 * get 公共冷却层级
		 * @return 
		 */
		public function get q_public_cd_level(): int{
			return _q_public_cd_level;
		}
		
		/**
		 * set 公共冷却层级
		 */
		public function set q_public_cd_level(value: int): void{
			this._q_public_cd_level = value;
		}
		
		/**
		 * get 该技能的克制技能编号
		 * @return 
		 */
		public function get q_restriction(): int{
			return _q_restriction;
		}
		
		/**
		 * set 该技能的克制技能编号
		 */
		public function set q_restriction(value: int): void{
			this._q_restriction = value;
		}
		
		/**
		 * get 是否触发一次战斗公式的伤害（0不触发，1触发）
		 * @return 
		 */
		public function get q_trigger_figth_hurt(): int{
			return _q_trigger_figth_hurt;
		}
		
		/**
		 * set 是否触发一次战斗公式的伤害（0不触发，1触发）
		 */
		public function set q_trigger_figth_hurt(value: int): void{
			this._q_trigger_figth_hurt = value;
		}
		
		/**
		 * get 伤害修正系数（万分比）
		 * @return 
		 */
		public function get q_hurt_correct_factor(): int{
			return _q_hurt_correct_factor;
		}
		
		/**
		 * set 伤害修正系数（万分比）
		 */
		public function set q_hurt_correct_factor(value: int): void{
			this._q_hurt_correct_factor = value;
		}
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get q_grade(): int{
			return _q_grade;
		}
		
		/**
		 * set 技能等级
		 */
		public function set q_grade(value: int): void{
			this._q_grade = value;
		}
		
		/**
		 * get 升级所需人物等级
		 * @return 
		 */
		public function get q_needgrade(): int{
			return _q_needgrade;
		}
		
		/**
		 * set 升级所需人物等级
		 */
		public function set q_needgrade(value: int): void{
			this._q_needgrade = value;
		}
		
		/**
		 * get 升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
		 * @return 
		 */
		public function get q_up_need_goods(): String{
			return _q_up_need_goods;
		}
		
		/**
		 * set 升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
		 */
		public function set q_up_need_goods(value: String): void{
			this._q_up_need_goods = value;
		}
		
		/**
		 * get 所需材料的出处说明（文字描述）
		 * @return 
		 */
		public function get q_up_need_goods_desc(): String{
			return _q_up_need_goods_desc;
		}
		
		/**
		 * set 所需材料的出处说明（文字描述）
		 */
		public function set q_up_need_goods_desc(value: String): void{
			this._q_up_need_goods_desc = value;
		}
		
		/**
		 * get 升级所需铜币
		 * @return 
		 */
		public function get q_up_need_copper(): int{
			return _q_up_need_copper;
		}
		
		/**
		 * set 升级所需铜币
		 */
		public function set q_up_need_copper(value: int): void{
			this._q_up_need_copper = value;
		}
		
		/**
		 * get 升级所需真气
		 * @return 
		 */
		public function get q_up_need_zhengqi(): int{
			return _q_up_need_zhengqi;
		}
		
		/**
		 * set 升级所需真气
		 */
		public function set q_up_need_zhengqi(value: int): void{
			this._q_up_need_zhengqi = value;
		}
		
		/**
		 * get 真气来源出处说明（文字描述）
		 * @return 
		 */
		public function get q_up_need_zhengqi_desc(): String{
			return _q_up_need_zhengqi_desc;
		}
		
		/**
		 * set 真气来源出处说明（文字描述）
		 */
		public function set q_up_need_zhengqi_desc(value: String): void{
			this._q_up_need_zhengqi_desc = value;
		}
		
		/**
		 * get 升级所需时间（单位：毫秒）
		 * @return 
		 */
		public function get q_up_need_time(): int{
			return _q_up_need_time;
		}
		
		/**
		 * set 升级所需时间（单位：毫秒）
		 */
		public function set q_up_need_time(value: int): void{
			this._q_up_need_time = value;
		}
		
		/**
		 * get 升级成功几率
		 * @return 
		 */
		public function get q_up_prob(): int{
			return _q_up_prob;
		}
		
		/**
		 * set 升级成功几率
		 */
		public function set q_up_prob(value: int): void{
			this._q_up_prob = value;
		}
		
		/**
		 * get 使用消耗内力值
		 * @return 
		 */
		public function get q_need_mp(): int{
			return _q_need_mp;
		}
		
		/**
		 * set 使用消耗内力值
		 */
		public function set q_need_mp(value: int): void{
			this._q_need_mp = value;
		}
		
		/**
		 * get 每次造成怪物仇恨值
		 * @return 
		 */
		public function get q_enmity(): int{
			return _q_enmity;
		}
		
		/**
		 * set 每次造成怪物仇恨值
		 */
		public function set q_enmity(value: int): void{
			this._q_enmity = value;
		}
		
		/**
		 * get 技能加成攻击力值
		 * @return 
		 */
		public function get q_attack_addition(): int{
			return _q_attack_addition;
		}
		
		/**
		 * set 技能加成攻击力值
		 */
		public function set q_attack_addition(value: int): void{
			this._q_attack_addition = value;
		}
		
		/**
		 * get 技能造成无视防御伤害值
		 * @return 
		 */
		public function get q_ignore_defence(): int{
			return _q_ignore_defence;
		}
		
		/**
		 * set 技能造成无视防御伤害值
		 */
		public function set q_ignore_defence(value: int): void{
			this._q_ignore_defence = value;
		}
		
		/**
		 * get 使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
		 * @return 
		 */
		public function get q_passive_buff(): String{
			return _q_passive_buff;
		}
		
		/**
		 * set 使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
		 */
		public function set q_passive_buff(value: String): void{
			this._q_passive_buff = value;
		}
		
		/**
		 * get 成功施加BUFF系数
		 * @return 
		 */
		public function get q_bufq_trigger_factor(): int{
			return _q_bufq_trigger_factor;
		}
		
		/**
		 * set 成功施加BUFF系数
		 */
		public function set q_bufq_trigger_factor(value: int): void{
			this._q_bufq_trigger_factor = value;
		}
		
		/**
		 * get 成功施加BUFF抵抗系数
		 * @return 
		 */
		public function get q_bufq_defence_factor(): int{
			return _q_bufq_defence_factor;
		}
		
		/**
		 * set 成功施加BUFF抵抗系数
		 */
		public function set q_bufq_defence_factor(value: int): void{
			this._q_bufq_defence_factor = value;
		}
		
		/**
		 * get BUFF持续时间提升系数
		 * @return 
		 */
		public function get q_bufq_timeup_factor(): int{
			return _q_bufq_timeup_factor;
		}
		
		/**
		 * set BUFF持续时间提升系数
		 */
		public function set q_bufq_timeup_factor(value: int): void{
			this._q_bufq_timeup_factor = value;
		}
		
		/**
		 * get BUFF持续时间减免系数
		 * @return 
		 */
		public function get q_bufq_timedown_factor(): int{
			return _q_bufq_timedown_factor;
		}
		
		/**
		 * set BUFF持续时间减免系数
		 */
		public function set q_bufq_timedown_factor(value: int): void{
			this._q_bufq_timedown_factor = value;
		}
		
		/**
		 * get BUFF作用数值修正系数
		 * @return 
		 */
		public function get q_bufq_num_factor(): int{
			return _q_bufq_num_factor;
		}
		
		/**
		 * set BUFF作用数值修正系数
		 */
		public function set q_bufq_num_factor(value: int): void{
			this._q_bufq_num_factor = value;
		}
		
		/**
		 * get BUFF作用比例修正系数
		 * @return 
		 */
		public function get q_bufq_action_factor(): int{
			return _q_bufq_action_factor;
		}
		
		/**
		 * set BUFF作用比例修正系数
		 */
		public function set q_bufq_action_factor(value: int): void{
			this._q_bufq_action_factor = value;
		}
		
		/**
		 * get 技能调用攻击动作编号
		 * @return 
		 */
		public function get q_attack_id(): int{
			return _q_attack_id;
		}
		
		/**
		 * set 技能调用攻击动作编号
		 */
		public function set q_attack_id(value: int): void{
			this._q_attack_id = value;
		}
		
		/**
		 * get 延迟命中时间（单位：毫秒）
		 * @return 
		 */
		public function get q_delay_time(): int{
			return _q_delay_time;
		}
		
		/**
		 * set 延迟命中时间（单位：毫秒）
		 */
		public function set q_delay_time(value: int): void{
			this._q_delay_time = value;
		}
		
		/**
		 * get 弹道飞行速度（单位：像素/秒）
		 * @return 
		 */
		public function get q_trajectory_speed(): int{
			return _q_trajectory_speed;
		}
		
		/**
		 * set 弹道飞行速度（单位：像素/秒）
		 */
		public function set q_trajectory_speed(value: int): void{
			this._q_trajectory_speed = value;
		}
		
		/**
		 * get 技能施法特效编号
		 * @return 
		 */
		public function get q_use_effect(): String{
			return _q_use_effect;
		}
		
		/**
		 * set 技能施法特效编号
		 */
		public function set q_use_effect(value: String): void{
			this._q_use_effect = value;
		}
		
		/**
		 * get 技能弹道特效编号
		 * @return 
		 */
		public function get q_trajectory_effect(): String{
			return _q_trajectory_effect;
		}
		
		/**
		 * set 技能弹道特效编号
		 */
		public function set q_trajectory_effect(value: String): void{
			this._q_trajectory_effect = value;
		}
		
		/**
		 * get 技能命中特效编号
		 * @return 
		 */
		public function get q_hit_effect(): String{
			return _q_hit_effect;
		}
		
		/**
		 * set 技能命中特效编号
		 */
		public function set q_hit_effect(value: String): void{
			this._q_hit_effect = value;
		}
		
		/**
		 * get 技能持续特效编号
		 * @return 
		 */
		public function get q_series_effect(): String{
			return _q_series_effect;
		}
		
		/**
		 * set 技能持续特效编号
		 */
		public function set q_series_effect(value: String): void{
			this._q_series_effect = value;
		}
		
		/**
		 * get 技能小图标编号（36*36）
		 * @return 
		 */
		public function get q_small_ico(): int{
			return _q_small_ico;
		}
		
		/**
		 * set 技能小图标编号（36*36）
		 */
		public function set q_small_ico(value: int): void{
			this._q_small_ico = value;
		}
		
		/**
		 * get 技能施法音效
		 * @return 
		 */
		public function get q_use_sound(): String{
			return _q_use_sound;
		}
		
		/**
		 * set 技能施法音效
		 */
		public function set q_use_sound(value: String): void{
			this._q_use_sound = value;
		}
		
		/**
		 * get 技能命中音效
		 * @return 
		 */
		public function get q_hit_sound(): String{
			return _q_hit_sound;
		}
		
		/**
		 * set 技能命中音效
		 */
		public function set q_hit_sound(value: String): void{
			this._q_hit_sound = value;
		}
		
		/**
		 * get （前端使用）战斗力加成
		 * @return 
		 */
		public function get q_fight_bonus(): int{
			return _q_fight_bonus;
		}
		
		/**
		 * set （前端使用）战斗力加成
		 */
		public function set q_fight_bonus(value: int): void{
			this._q_fight_bonus = value;
		}
		
		/**
		 * get 最大等级
		 * @return 
		 */
		public function get q_max_level(): int{
			return _q_max_level;
		}
		
		/**
		 * set 最大等级
		 */
		public function set q_max_level(value: int): void{
			this._q_max_level = value;
		}
		
		/**
		 * get 跳跃是否可使用技能(1是，0否)
		 * @return 
		 */
		public function get q_is_Jump_skill(): int{
			return _q_is_Jump_skill;
		}
		
		/**
		 * set 跳跃是否可使用技能(1是，0否)
		 */
		public function set q_is_Jump_skill(value: int): void{
			this._q_is_Jump_skill = value;
		}
		
		/**
		 * get 调用脚本编号
		 * @return 
		 */
		public function get q_skill_script(): int{
			return _q_skill_script;
		}
		
		/**
		 * set 调用脚本编号
		 */
		public function set q_skill_script(value: int): void{
			this._q_skill_script = value;
		}
		
		/**
		 * get 是否忽视（防御，闪避，跳跃）
		 * @return 
		 */
		public function get q_is_ignore(): int{
			return _q_is_ignore;
		}
		
		/**
		 * set 是否忽视（防御，闪避，跳跃）
		 */
		public function set q_is_ignore(value: int): void{
			this._q_is_ignore = value;
		}
		
		/**
		 * get 技能类型（0普通技能，1火墙类型）
		 * @return 
		 */
		public function get q_skill_type(): int{
			return _q_skill_type;
		}
		
		/**
		 * set 技能类型（0普通技能，1火墙类型）
		 */
		public function set q_skill_type(value: int): void{
			this._q_skill_type = value;
		}
		
		/**
		 * get 技能持续时间（毫秒）
		 * @return 
		 */
		public function get q_skill_time(): int{
			return _q_skill_time;
		}
		
		/**
		 * set 技能持续时间（毫秒）
		 */
		public function set q_skill_time(value: int): void{
			this._q_skill_time = value;
		}
		
	}
}