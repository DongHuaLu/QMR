package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_monster
	 */
	public class Q_monster extends Bean{
	
		//怪物ID
		private var _q_id: int;
		
		//怪物名字
		private var _q_name: String;
		
		//怪物爆率文字描述（小地图tips描述，支持html）
		private var _q_monster_dropdesc: String;
		
		//是否在小地图中显示（1是，2否）
		private var _q_isminimap: int;
		
		//怪物类型(1普通小怪,2精英,3BOSS)
		private var _q_monster_type: int;
		
		//怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
		private var _q_evasive_style: int;
		
		//怪物造型资源编号
		private var _q_sculpt_resid: String;
		
		//怪物头像资源编号
		private var _q_head_resid: String;
		
		//攻击时音效编号
		private var _q_fire_soundid: String;
		
		//被攻击时音效编号
		private var _q_underfire_soundid: String;
		
		//死亡时音效编号
		private var _q_die_soundid: String;
		
		//怪物在场景中的发言频率间隔(单位：毫秒)
		private var _q_tosay_timeinterval: int;
		
		//怪物在场景中的发言内容(多句以分号分隔)
		private var _q_say_condition: String;
		
		//怪物等级
		private var _q_grade: int;
		
		//生命值
		private var _q_maxhp: int;
		
		//内力
		private var _q_maxmp: int;
		
		//体力
		private var _q_maxsp: int;
		
		//攻击力
		private var _q_attack: int;
		
		//无视防御伤害（忽视玩家防御，可以被元气盾反弹）
		private var _q_ignore_damage: int;
		
		//武器攻击（可以被玩家的化龙破技能清0）
		private var _q_equip_attack: int;
		
		//防御力
		private var _q_defense: int;
		
		//护甲防御（可以被玩家的破甲击技能清零）
		private var _q_equip_defense: int;
		
		//暴击值
		private var _q_crt: int;
		
		//闪避值
		private var _q_dodge: int;
		
		//攻击速度
		private var _q_attack_speed: int;
		
		//移动速度
		private var _q_speed: int;
		
		//幸运值
		private var _q_luck: int;
		
		//怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
		private var _q_variation: String;
		
		//视野距离半径(单位：格子数)
		private var _q_eyeshot: int;
		
		//巡逻距离半径(单位：格子数)
		private var _q_patrol: int;
		
		//巡逻间隔时间（毫秒）
		private var _q_patrol_time: int;
		
		//巡逻几率（万分比）
		private var _q_patrol_pro: int;
		
		//追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
		private var _q_pursuit: int;
		
		//怪物攻击时使用的默认技能(格式：技能ID_技能等级）
		private var _q_default_skill: String;
		
		//怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
		private var _q_special_skill: String;
		
		//怪物被攻击时是否固定少血(0否,1是)
		private var _q_fixed_hurt: int;
		
		//怪物被攻击时固定少血值
		private var _q_fiexd_value: int;
		
		//怪物携带经验
		private var _q_carry_exp: int;
		
		//怪物携带军功值
		private var _q_ranknum: int;
		
		//是否有额外军功值 0为无军功值 N为额外的军功值数值
		private var _q_extra_ranknum: int;
		
		//是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
		private var _q_isexclude: int;
		
		//爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
		private var _q_intensify_prob: String;
		
		//爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
		private var _q_addition_prob: String;
		
		//BOSS类怪物，固定刷新时间定义
		private var _q_refreshtime: String;
		
		//怪物的重生时间(单位：秒)
		private var _q_revive_time: int;
		
		//关联的AI脚本ID
		private var _q_script_id: int;
		
		//是否跨服同步信息（0否）
		private var _q_info_sync: int;
		
		//[{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
		private var _q_monster_ai: String;
		
		//怪物被动技能
		private var _q_passive_skill: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物ID
			_q_id = readInt();
			//怪物名字
			_q_name = readString();
			//怪物爆率文字描述（小地图tips描述，支持html）
			_q_monster_dropdesc = readString();
			//是否在小地图中显示（1是，2否）
			_q_isminimap = readInt();
			//怪物类型(1普通小怪,2精英,3BOSS)
			_q_monster_type = readInt();
			//怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
			_q_evasive_style = readInt();
			//怪物造型资源编号
			_q_sculpt_resid = readString();
			//怪物头像资源编号
			_q_head_resid = readString();
			//攻击时音效编号
			_q_fire_soundid = readString();
			//被攻击时音效编号
			_q_underfire_soundid = readString();
			//死亡时音效编号
			_q_die_soundid = readString();
			//怪物在场景中的发言频率间隔(单位：毫秒)
			_q_tosay_timeinterval = readInt();
			//怪物在场景中的发言内容(多句以分号分隔)
			_q_say_condition = readString();
			//怪物等级
			_q_grade = readInt();
			//生命值
			_q_maxhp = readInt();
			//内力
			_q_maxmp = readInt();
			//体力
			_q_maxsp = readInt();
			//攻击力
			_q_attack = readInt();
			//无视防御伤害（忽视玩家防御，可以被元气盾反弹）
			_q_ignore_damage = readInt();
			//武器攻击（可以被玩家的化龙破技能清0）
			_q_equip_attack = readInt();
			//防御力
			_q_defense = readInt();
			//护甲防御（可以被玩家的破甲击技能清零）
			_q_equip_defense = readInt();
			//暴击值
			_q_crt = readInt();
			//闪避值
			_q_dodge = readInt();
			//攻击速度
			_q_attack_speed = readInt();
			//移动速度
			_q_speed = readInt();
			//幸运值
			_q_luck = readInt();
			//怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
			_q_variation = readString();
			//视野距离半径(单位：格子数)
			_q_eyeshot = readInt();
			//巡逻距离半径(单位：格子数)
			_q_patrol = readInt();
			//巡逻间隔时间（毫秒）
			_q_patrol_time = readInt();
			//巡逻几率（万分比）
			_q_patrol_pro = readInt();
			//追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
			_q_pursuit = readInt();
			//怪物攻击时使用的默认技能(格式：技能ID_技能等级）
			_q_default_skill = readString();
			//怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
			_q_special_skill = readString();
			//怪物被攻击时是否固定少血(0否,1是)
			_q_fixed_hurt = readInt();
			//怪物被攻击时固定少血值
			_q_fiexd_value = readInt();
			//怪物携带经验
			_q_carry_exp = readInt();
			//怪物携带军功值
			_q_ranknum = readInt();
			//是否有额外军功值 0为无军功值 N为额外的军功值数值
			_q_extra_ranknum = readInt();
			//是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
			_q_isexclude = readInt();
			//爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
			_q_intensify_prob = readString();
			//爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
			_q_addition_prob = readString();
			//BOSS类怪物，固定刷新时间定义
			_q_refreshtime = readString();
			//怪物的重生时间(单位：秒)
			_q_revive_time = readInt();
			//关联的AI脚本ID
			_q_script_id = readInt();
			//是否跨服同步信息（0否）
			_q_info_sync = readInt();
			//[{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
			_q_monster_ai = readString();
			//怪物被动技能
			_q_passive_skill = readString();
			return true;
		}
		
		/**
		 * get 怪物ID
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 怪物ID
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 怪物名字
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 怪物名字
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 怪物爆率文字描述（小地图tips描述，支持html）
		 * @return 
		 */
		public function get q_monster_dropdesc(): String{
			return _q_monster_dropdesc;
		}
		
		/**
		 * set 怪物爆率文字描述（小地图tips描述，支持html）
		 */
		public function set q_monster_dropdesc(value: String): void{
			this._q_monster_dropdesc = value;
		}
		
		/**
		 * get 是否在小地图中显示（1是，2否）
		 * @return 
		 */
		public function get q_isminimap(): int{
			return _q_isminimap;
		}
		
		/**
		 * set 是否在小地图中显示（1是，2否）
		 */
		public function set q_isminimap(value: int): void{
			this._q_isminimap = value;
		}
		
		/**
		 * get 怪物类型(1普通小怪,2精英,3BOSS)
		 * @return 
		 */
		public function get q_monster_type(): int{
			return _q_monster_type;
		}
		
		/**
		 * set 怪物类型(1普通小怪,2精英,3BOSS)
		 */
		public function set q_monster_type(value: int): void{
			this._q_monster_type = value;
		}
		
		/**
		 * get 怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
		 * @return 
		 */
		public function get q_evasive_style(): int{
			return _q_evasive_style;
		}
		
		/**
		 * set 怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
		 */
		public function set q_evasive_style(value: int): void{
			this._q_evasive_style = value;
		}
		
		/**
		 * get 怪物造型资源编号
		 * @return 
		 */
		public function get q_sculpt_resid(): String{
			return _q_sculpt_resid;
		}
		
		/**
		 * set 怪物造型资源编号
		 */
		public function set q_sculpt_resid(value: String): void{
			this._q_sculpt_resid = value;
		}
		
		/**
		 * get 怪物头像资源编号
		 * @return 
		 */
		public function get q_head_resid(): String{
			return _q_head_resid;
		}
		
		/**
		 * set 怪物头像资源编号
		 */
		public function set q_head_resid(value: String): void{
			this._q_head_resid = value;
		}
		
		/**
		 * get 攻击时音效编号
		 * @return 
		 */
		public function get q_fire_soundid(): String{
			return _q_fire_soundid;
		}
		
		/**
		 * set 攻击时音效编号
		 */
		public function set q_fire_soundid(value: String): void{
			this._q_fire_soundid = value;
		}
		
		/**
		 * get 被攻击时音效编号
		 * @return 
		 */
		public function get q_underfire_soundid(): String{
			return _q_underfire_soundid;
		}
		
		/**
		 * set 被攻击时音效编号
		 */
		public function set q_underfire_soundid(value: String): void{
			this._q_underfire_soundid = value;
		}
		
		/**
		 * get 死亡时音效编号
		 * @return 
		 */
		public function get q_die_soundid(): String{
			return _q_die_soundid;
		}
		
		/**
		 * set 死亡时音效编号
		 */
		public function set q_die_soundid(value: String): void{
			this._q_die_soundid = value;
		}
		
		/**
		 * get 怪物在场景中的发言频率间隔(单位：毫秒)
		 * @return 
		 */
		public function get q_tosay_timeinterval(): int{
			return _q_tosay_timeinterval;
		}
		
		/**
		 * set 怪物在场景中的发言频率间隔(单位：毫秒)
		 */
		public function set q_tosay_timeinterval(value: int): void{
			this._q_tosay_timeinterval = value;
		}
		
		/**
		 * get 怪物在场景中的发言内容(多句以分号分隔)
		 * @return 
		 */
		public function get q_say_condition(): String{
			return _q_say_condition;
		}
		
		/**
		 * set 怪物在场景中的发言内容(多句以分号分隔)
		 */
		public function set q_say_condition(value: String): void{
			this._q_say_condition = value;
		}
		
		/**
		 * get 怪物等级
		 * @return 
		 */
		public function get q_grade(): int{
			return _q_grade;
		}
		
		/**
		 * set 怪物等级
		 */
		public function set q_grade(value: int): void{
			this._q_grade = value;
		}
		
		/**
		 * get 生命值
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 生命值
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 内力
		 * @return 
		 */
		public function get q_maxmp(): int{
			return _q_maxmp;
		}
		
		/**
		 * set 内力
		 */
		public function set q_maxmp(value: int): void{
			this._q_maxmp = value;
		}
		
		/**
		 * get 体力
		 * @return 
		 */
		public function get q_maxsp(): int{
			return _q_maxsp;
		}
		
		/**
		 * set 体力
		 */
		public function set q_maxsp(value: int): void{
			this._q_maxsp = value;
		}
		
		/**
		 * get 攻击力
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击力
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 无视防御伤害（忽视玩家防御，可以被元气盾反弹）
		 * @return 
		 */
		public function get q_ignore_damage(): int{
			return _q_ignore_damage;
		}
		
		/**
		 * set 无视防御伤害（忽视玩家防御，可以被元气盾反弹）
		 */
		public function set q_ignore_damage(value: int): void{
			this._q_ignore_damage = value;
		}
		
		/**
		 * get 武器攻击（可以被玩家的化龙破技能清0）
		 * @return 
		 */
		public function get q_equip_attack(): int{
			return _q_equip_attack;
		}
		
		/**
		 * set 武器攻击（可以被玩家的化龙破技能清0）
		 */
		public function set q_equip_attack(value: int): void{
			this._q_equip_attack = value;
		}
		
		/**
		 * get 防御力
		 * @return 
		 */
		public function get q_defense(): int{
			return _q_defense;
		}
		
		/**
		 * set 防御力
		 */
		public function set q_defense(value: int): void{
			this._q_defense = value;
		}
		
		/**
		 * get 护甲防御（可以被玩家的破甲击技能清零）
		 * @return 
		 */
		public function get q_equip_defense(): int{
			return _q_equip_defense;
		}
		
		/**
		 * set 护甲防御（可以被玩家的破甲击技能清零）
		 */
		public function set q_equip_defense(value: int): void{
			this._q_equip_defense = value;
		}
		
		/**
		 * get 暴击值
		 * @return 
		 */
		public function get q_crt(): int{
			return _q_crt;
		}
		
		/**
		 * set 暴击值
		 */
		public function set q_crt(value: int): void{
			this._q_crt = value;
		}
		
		/**
		 * get 闪避值
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 闪避值
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 攻击速度
		 * @return 
		 */
		public function get q_attack_speed(): int{
			return _q_attack_speed;
		}
		
		/**
		 * set 攻击速度
		 */
		public function set q_attack_speed(value: int): void{
			this._q_attack_speed = value;
		}
		
		/**
		 * get 移动速度
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 移动速度
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 幸运值
		 * @return 
		 */
		public function get q_luck(): int{
			return _q_luck;
		}
		
		/**
		 * set 幸运值
		 */
		public function set q_luck(value: int): void{
			this._q_luck = value;
		}
		
		/**
		 * get 怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
		 * @return 
		 */
		public function get q_variation(): String{
			return _q_variation;
		}
		
		/**
		 * set 怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
		 */
		public function set q_variation(value: String): void{
			this._q_variation = value;
		}
		
		/**
		 * get 视野距离半径(单位：格子数)
		 * @return 
		 */
		public function get q_eyeshot(): int{
			return _q_eyeshot;
		}
		
		/**
		 * set 视野距离半径(单位：格子数)
		 */
		public function set q_eyeshot(value: int): void{
			this._q_eyeshot = value;
		}
		
		/**
		 * get 巡逻距离半径(单位：格子数)
		 * @return 
		 */
		public function get q_patrol(): int{
			return _q_patrol;
		}
		
		/**
		 * set 巡逻距离半径(单位：格子数)
		 */
		public function set q_patrol(value: int): void{
			this._q_patrol = value;
		}
		
		/**
		 * get 巡逻间隔时间（毫秒）
		 * @return 
		 */
		public function get q_patrol_time(): int{
			return _q_patrol_time;
		}
		
		/**
		 * set 巡逻间隔时间（毫秒）
		 */
		public function set q_patrol_time(value: int): void{
			this._q_patrol_time = value;
		}
		
		/**
		 * get 巡逻几率（万分比）
		 * @return 
		 */
		public function get q_patrol_pro(): int{
			return _q_patrol_pro;
		}
		
		/**
		 * set 巡逻几率（万分比）
		 */
		public function set q_patrol_pro(value: int): void{
			this._q_patrol_pro = value;
		}
		
		/**
		 * get 追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
		 * @return 
		 */
		public function get q_pursuit(): int{
			return _q_pursuit;
		}
		
		/**
		 * set 追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
		 */
		public function set q_pursuit(value: int): void{
			this._q_pursuit = value;
		}
		
		/**
		 * get 怪物攻击时使用的默认技能(格式：技能ID_技能等级）
		 * @return 
		 */
		public function get q_default_skill(): String{
			return _q_default_skill;
		}
		
		/**
		 * set 怪物攻击时使用的默认技能(格式：技能ID_技能等级）
		 */
		public function set q_default_skill(value: String): void{
			this._q_default_skill = value;
		}
		
		/**
		 * get 怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
		 * @return 
		 */
		public function get q_special_skill(): String{
			return _q_special_skill;
		}
		
		/**
		 * set 怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
		 */
		public function set q_special_skill(value: String): void{
			this._q_special_skill = value;
		}
		
		/**
		 * get 怪物被攻击时是否固定少血(0否,1是)
		 * @return 
		 */
		public function get q_fixed_hurt(): int{
			return _q_fixed_hurt;
		}
		
		/**
		 * set 怪物被攻击时是否固定少血(0否,1是)
		 */
		public function set q_fixed_hurt(value: int): void{
			this._q_fixed_hurt = value;
		}
		
		/**
		 * get 怪物被攻击时固定少血值
		 * @return 
		 */
		public function get q_fiexd_value(): int{
			return _q_fiexd_value;
		}
		
		/**
		 * set 怪物被攻击时固定少血值
		 */
		public function set q_fiexd_value(value: int): void{
			this._q_fiexd_value = value;
		}
		
		/**
		 * get 怪物携带经验
		 * @return 
		 */
		public function get q_carry_exp(): int{
			return _q_carry_exp;
		}
		
		/**
		 * set 怪物携带经验
		 */
		public function set q_carry_exp(value: int): void{
			this._q_carry_exp = value;
		}
		
		/**
		 * get 怪物携带军功值
		 * @return 
		 */
		public function get q_ranknum(): int{
			return _q_ranknum;
		}
		
		/**
		 * set 怪物携带军功值
		 */
		public function set q_ranknum(value: int): void{
			this._q_ranknum = value;
		}
		
		/**
		 * get 是否有额外军功值 0为无军功值 N为额外的军功值数值
		 * @return 
		 */
		public function get q_extra_ranknum(): int{
			return _q_extra_ranknum;
		}
		
		/**
		 * set 是否有额外军功值 0为无军功值 N为额外的军功值数值
		 */
		public function set q_extra_ranknum(value: int): void{
			this._q_extra_ranknum = value;
		}
		
		/**
		 * get 是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
		 * @return 
		 */
		public function get q_isexclude(): int{
			return _q_isexclude;
		}
		
		/**
		 * set 是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
		 */
		public function set q_isexclude(value: int): void{
			this._q_isexclude = value;
		}
		
		/**
		 * get 爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
		 * @return 
		 */
		public function get q_intensify_prob(): String{
			return _q_intensify_prob;
		}
		
		/**
		 * set 爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
		 */
		public function set q_intensify_prob(value: String): void{
			this._q_intensify_prob = value;
		}
		
		/**
		 * get 爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
		 * @return 
		 */
		public function get q_addition_prob(): String{
			return _q_addition_prob;
		}
		
		/**
		 * set 爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
		 */
		public function set q_addition_prob(value: String): void{
			this._q_addition_prob = value;
		}
		
		/**
		 * get BOSS类怪物，固定刷新时间定义
		 * @return 
		 */
		public function get q_refreshtime(): String{
			return _q_refreshtime;
		}
		
		/**
		 * set BOSS类怪物，固定刷新时间定义
		 */
		public function set q_refreshtime(value: String): void{
			this._q_refreshtime = value;
		}
		
		/**
		 * get 怪物的重生时间(单位：秒)
		 * @return 
		 */
		public function get q_revive_time(): int{
			return _q_revive_time;
		}
		
		/**
		 * set 怪物的重生时间(单位：秒)
		 */
		public function set q_revive_time(value: int): void{
			this._q_revive_time = value;
		}
		
		/**
		 * get 关联的AI脚本ID
		 * @return 
		 */
		public function get q_script_id(): int{
			return _q_script_id;
		}
		
		/**
		 * set 关联的AI脚本ID
		 */
		public function set q_script_id(value: int): void{
			this._q_script_id = value;
		}
		
		/**
		 * get 是否跨服同步信息（0否）
		 * @return 
		 */
		public function get q_info_sync(): int{
			return _q_info_sync;
		}
		
		/**
		 * set 是否跨服同步信息（0否）
		 */
		public function set q_info_sync(value: int): void{
			this._q_info_sync = value;
		}
		
		/**
		 * get [{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
		 * @return 
		 */
		public function get q_monster_ai(): String{
			return _q_monster_ai;
		}
		
		/**
		 * set [{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
		 */
		public function set q_monster_ai(value: String): void{
			this._q_monster_ai = value;
		}
		
		/**
		 * get 怪物被动技能
		 * @return 
		 */
		public function get q_passive_skill(): String{
			return _q_passive_skill;
		}
		
		/**
		 * set 怪物被动技能
		 */
		public function set q_passive_skill(value: String): void{
			this._q_passive_skill = value;
		}
		
	}
}