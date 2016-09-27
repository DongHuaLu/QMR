package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_buff
	 */
	public class Q_buff extends Bean{
	
		//BUFF编号
		private var _q_buff_id: int;
		
		//BUFF名称
		private var _q_buff_name: String;
		
		//获得BUFF时向获得者发送提示信息
		private var _q_effect_prompt: String;
		
		//作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
		private var _q_target: int;
		
		//获得BUFF时向施加者发送提示信息
		private var _q_user_prompt: String;
		
		//BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
		private var _q_cast_type: int;
		
		//损益类型（0无所谓，1正面BUFF，2负面BUFF,3不受内力盾影响）
		private var _q_effect_type: int;
		
		//效果类型
		private var _q_action_type: int;
		
		//效果添加成功几率（本处填万分比的分子）
		private var _q_trigger_prob: int;
		
		//效果作用数值（允许负值）
		private var _q_effect_value: int;
		
		//效果作用比例（本处填万分比的分子）（允许负值）
		private var _q_effect_ratio: int;
		
		//效果的总持续时间（单位：秒）（填-1为永久生效）
		private var _q_effect_time: int;
		
		//效果的总容量
		private var _q_effect_maxvalue: int;
		
		//效果分次作用间隔时间（单位：毫秒）
		private var _q_effect_cooldown: int;
		
		//效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
		private var _q_overlay: int;
		
		//叠加次数上限（-1为无限）
		private var _q_overlay_maxcount: int;
		
		//替换层级
		private var _q_replace_level: int;
		
		//获得者死亡或下线后是否清除（0不清除，1清除）
		private var _q_effect_dieordown_clear: int;
		
		//施加者死亡或下线后是否清除（0不清除，1清除）
		private var _q_user_dieordown_clear: int;
		
		//获得BUFF特效编号
		private var _q_small_ico: String;
		
		//BUFF图标（36*36）
		private var _q_ico: String;
		
		//显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
		private var _q_swf: String;
		
		//鼠标TIPS界面上的描述信息（支持Html）
		private var _q_tips: String;
		
		//BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。总移动速度比例：X】
		private var _q_Bonus_skill: String;
		
		//下线是否计时（0不是，1是）
		private var _q_Line_time: int;
		
		//触发几率
		private var _q_trigger_probability: int;
		
		//是否受VIP时间加成（1是，0否）
		private var _q_vip_bonus: int;
		
		//脚本BUFFID
		private var _q_script_id: int;
		
		//是否受技能玲珑霸体影响（[1001][1002]）
		private var _q_skill_influnce: String;
		
		//针对目标（1玩家）
		private var _q_target_type: int;
		
		//拥有BUFF者是否变色（0否，1是）
		private var _q_colour: int;
		
		//是否加成战斗力（1加成，0不加成）
		private var _q_fihgt: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//BUFF编号
			_q_buff_id = readInt();
			//BUFF名称
			_q_buff_name = readString();
			//获得BUFF时向获得者发送提示信息
			_q_effect_prompt = readString();
			//作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
			_q_target = readInt();
			//获得BUFF时向施加者发送提示信息
			_q_user_prompt = readString();
			//BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
			_q_cast_type = readInt();
			//损益类型（0无所谓，1正面BUFF，2负面BUFF,3不受内力盾影响）
			_q_effect_type = readInt();
			//效果类型
			_q_action_type = readInt();
			//效果添加成功几率（本处填万分比的分子）
			_q_trigger_prob = readInt();
			//效果作用数值（允许负值）
			_q_effect_value = readInt();
			//效果作用比例（本处填万分比的分子）（允许负值）
			_q_effect_ratio = readInt();
			//效果的总持续时间（单位：秒）（填-1为永久生效）
			_q_effect_time = readInt();
			//效果的总容量
			_q_effect_maxvalue = readInt();
			//效果分次作用间隔时间（单位：毫秒）
			_q_effect_cooldown = readInt();
			//效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
			_q_overlay = readInt();
			//叠加次数上限（-1为无限）
			_q_overlay_maxcount = readInt();
			//替换层级
			_q_replace_level = readInt();
			//获得者死亡或下线后是否清除（0不清除，1清除）
			_q_effect_dieordown_clear = readInt();
			//施加者死亡或下线后是否清除（0不清除，1清除）
			_q_user_dieordown_clear = readInt();
			//获得BUFF特效编号
			_q_small_ico = readString();
			//BUFF图标（36*36）
			_q_ico = readString();
			//显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
			_q_swf = readString();
			//鼠标TIPS界面上的描述信息（支持Html）
			_q_tips = readString();
			//BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。总移动速度比例：X】
			_q_Bonus_skill = readString();
			//下线是否计时（0不是，1是）
			_q_Line_time = readInt();
			//触发几率
			_q_trigger_probability = readInt();
			//是否受VIP时间加成（1是，0否）
			_q_vip_bonus = readInt();
			//脚本BUFFID
			_q_script_id = readInt();
			//是否受技能玲珑霸体影响（[1001][1002]）
			_q_skill_influnce = readString();
			//针对目标（1玩家）
			_q_target_type = readInt();
			//拥有BUFF者是否变色（0否，1是）
			_q_colour = readInt();
			//是否加成战斗力（1加成，0不加成）
			_q_fihgt = readInt();
			return true;
		}
		
		/**
		 * get BUFF编号
		 * @return 
		 */
		public function get q_buff_id(): int{
			return _q_buff_id;
		}
		
		/**
		 * set BUFF编号
		 */
		public function set q_buff_id(value: int): void{
			this._q_buff_id = value;
		}
		
		/**
		 * get BUFF名称
		 * @return 
		 */
		public function get q_buff_name(): String{
			return _q_buff_name;
		}
		
		/**
		 * set BUFF名称
		 */
		public function set q_buff_name(value: String): void{
			this._q_buff_name = value;
		}
		
		/**
		 * get 获得BUFF时向获得者发送提示信息
		 * @return 
		 */
		public function get q_effect_prompt(): String{
			return _q_effect_prompt;
		}
		
		/**
		 * set 获得BUFF时向获得者发送提示信息
		 */
		public function set q_effect_prompt(value: String): void{
			this._q_effect_prompt = value;
		}
		
		/**
		 * get 作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
		 * @return 
		 */
		public function get q_target(): int{
			return _q_target;
		}
		
		/**
		 * set 作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
		 */
		public function set q_target(value: int): void{
			this._q_target = value;
		}
		
		/**
		 * get 获得BUFF时向施加者发送提示信息
		 * @return 
		 */
		public function get q_user_prompt(): String{
			return _q_user_prompt;
		}
		
		/**
		 * set 获得BUFF时向施加者发送提示信息
		 */
		public function set q_user_prompt(value: String): void{
			this._q_user_prompt = value;
		}
		
		/**
		 * get BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
		 * @return 
		 */
		public function get q_cast_type(): int{
			return _q_cast_type;
		}
		
		/**
		 * set BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
		 */
		public function set q_cast_type(value: int): void{
			this._q_cast_type = value;
		}
		
		/**
		 * get 损益类型（0无所谓，1正面BUFF，2负面BUFF,3不受内力盾影响）
		 * @return 
		 */
		public function get q_effect_type(): int{
			return _q_effect_type;
		}
		
		/**
		 * set 损益类型（0无所谓，1正面BUFF，2负面BUFF,3不受内力盾影响）
		 */
		public function set q_effect_type(value: int): void{
			this._q_effect_type = value;
		}
		
		/**
		 * get 效果类型
		 * @return 
		 */
		public function get q_action_type(): int{
			return _q_action_type;
		}
		
		/**
		 * set 效果类型
		 */
		public function set q_action_type(value: int): void{
			this._q_action_type = value;
		}
		
		/**
		 * get 效果添加成功几率（本处填万分比的分子）
		 * @return 
		 */
		public function get q_trigger_prob(): int{
			return _q_trigger_prob;
		}
		
		/**
		 * set 效果添加成功几率（本处填万分比的分子）
		 */
		public function set q_trigger_prob(value: int): void{
			this._q_trigger_prob = value;
		}
		
		/**
		 * get 效果作用数值（允许负值）
		 * @return 
		 */
		public function get q_effect_value(): int{
			return _q_effect_value;
		}
		
		/**
		 * set 效果作用数值（允许负值）
		 */
		public function set q_effect_value(value: int): void{
			this._q_effect_value = value;
		}
		
		/**
		 * get 效果作用比例（本处填万分比的分子）（允许负值）
		 * @return 
		 */
		public function get q_effect_ratio(): int{
			return _q_effect_ratio;
		}
		
		/**
		 * set 效果作用比例（本处填万分比的分子）（允许负值）
		 */
		public function set q_effect_ratio(value: int): void{
			this._q_effect_ratio = value;
		}
		
		/**
		 * get 效果的总持续时间（单位：秒）（填-1为永久生效）
		 * @return 
		 */
		public function get q_effect_time(): int{
			return _q_effect_time;
		}
		
		/**
		 * set 效果的总持续时间（单位：秒）（填-1为永久生效）
		 */
		public function set q_effect_time(value: int): void{
			this._q_effect_time = value;
		}
		
		/**
		 * get 效果的总容量
		 * @return 
		 */
		public function get q_effect_maxvalue(): int{
			return _q_effect_maxvalue;
		}
		
		/**
		 * set 效果的总容量
		 */
		public function set q_effect_maxvalue(value: int): void{
			this._q_effect_maxvalue = value;
		}
		
		/**
		 * get 效果分次作用间隔时间（单位：毫秒）
		 * @return 
		 */
		public function get q_effect_cooldown(): int{
			return _q_effect_cooldown;
		}
		
		/**
		 * set 效果分次作用间隔时间（单位：毫秒）
		 */
		public function set q_effect_cooldown(value: int): void{
			this._q_effect_cooldown = value;
		}
		
		/**
		 * get 效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
		 * @return 
		 */
		public function get q_overlay(): int{
			return _q_overlay;
		}
		
		/**
		 * set 效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
		 */
		public function set q_overlay(value: int): void{
			this._q_overlay = value;
		}
		
		/**
		 * get 叠加次数上限（-1为无限）
		 * @return 
		 */
		public function get q_overlay_maxcount(): int{
			return _q_overlay_maxcount;
		}
		
		/**
		 * set 叠加次数上限（-1为无限）
		 */
		public function set q_overlay_maxcount(value: int): void{
			this._q_overlay_maxcount = value;
		}
		
		/**
		 * get 替换层级
		 * @return 
		 */
		public function get q_replace_level(): int{
			return _q_replace_level;
		}
		
		/**
		 * set 替换层级
		 */
		public function set q_replace_level(value: int): void{
			this._q_replace_level = value;
		}
		
		/**
		 * get 获得者死亡或下线后是否清除（0不清除，1清除）
		 * @return 
		 */
		public function get q_effect_dieordown_clear(): int{
			return _q_effect_dieordown_clear;
		}
		
		/**
		 * set 获得者死亡或下线后是否清除（0不清除，1清除）
		 */
		public function set q_effect_dieordown_clear(value: int): void{
			this._q_effect_dieordown_clear = value;
		}
		
		/**
		 * get 施加者死亡或下线后是否清除（0不清除，1清除）
		 * @return 
		 */
		public function get q_user_dieordown_clear(): int{
			return _q_user_dieordown_clear;
		}
		
		/**
		 * set 施加者死亡或下线后是否清除（0不清除，1清除）
		 */
		public function set q_user_dieordown_clear(value: int): void{
			this._q_user_dieordown_clear = value;
		}
		
		/**
		 * get 获得BUFF特效编号
		 * @return 
		 */
		public function get q_small_ico(): String{
			return _q_small_ico;
		}
		
		/**
		 * set 获得BUFF特效编号
		 */
		public function set q_small_ico(value: String): void{
			this._q_small_ico = value;
		}
		
		/**
		 * get BUFF图标（36*36）
		 * @return 
		 */
		public function get q_ico(): String{
			return _q_ico;
		}
		
		/**
		 * set BUFF图标（36*36）
		 */
		public function set q_ico(value: String): void{
			this._q_ico = value;
		}
		
		/**
		 * get 显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
		 * @return 
		 */
		public function get q_swf(): String{
			return _q_swf;
		}
		
		/**
		 * set 显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
		 */
		public function set q_swf(value: String): void{
			this._q_swf = value;
		}
		
		/**
		 * get 鼠标TIPS界面上的描述信息（支持Html）
		 * @return 
		 */
		public function get q_tips(): String{
			return _q_tips;
		}
		
		/**
		 * set 鼠标TIPS界面上的描述信息（支持Html）
		 */
		public function set q_tips(value: String): void{
			this._q_tips = value;
		}
		
		/**
		 * get BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。总移动速度比例：X】
		 * @return 
		 */
		public function get q_Bonus_skill(): String{
			return _q_Bonus_skill;
		}
		
		/**
		 * set BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。总移动速度比例：X】
		 */
		public function set q_Bonus_skill(value: String): void{
			this._q_Bonus_skill = value;
		}
		
		/**
		 * get 下线是否计时（0不是，1是）
		 * @return 
		 */
		public function get q_Line_time(): int{
			return _q_Line_time;
		}
		
		/**
		 * set 下线是否计时（0不是，1是）
		 */
		public function set q_Line_time(value: int): void{
			this._q_Line_time = value;
		}
		
		/**
		 * get 触发几率
		 * @return 
		 */
		public function get q_trigger_probability(): int{
			return _q_trigger_probability;
		}
		
		/**
		 * set 触发几率
		 */
		public function set q_trigger_probability(value: int): void{
			this._q_trigger_probability = value;
		}
		
		/**
		 * get 是否受VIP时间加成（1是，0否）
		 * @return 
		 */
		public function get q_vip_bonus(): int{
			return _q_vip_bonus;
		}
		
		/**
		 * set 是否受VIP时间加成（1是，0否）
		 */
		public function set q_vip_bonus(value: int): void{
			this._q_vip_bonus = value;
		}
		
		/**
		 * get 脚本BUFFID
		 * @return 
		 */
		public function get q_script_id(): int{
			return _q_script_id;
		}
		
		/**
		 * set 脚本BUFFID
		 */
		public function set q_script_id(value: int): void{
			this._q_script_id = value;
		}
		
		/**
		 * get 是否受技能玲珑霸体影响（[1001][1002]）
		 * @return 
		 */
		public function get q_skill_influnce(): String{
			return _q_skill_influnce;
		}
		
		/**
		 * set 是否受技能玲珑霸体影响（[1001][1002]）
		 */
		public function set q_skill_influnce(value: String): void{
			this._q_skill_influnce = value;
		}
		
		/**
		 * get 针对目标（1玩家）
		 * @return 
		 */
		public function get q_target_type(): int{
			return _q_target_type;
		}
		
		/**
		 * set 针对目标（1玩家）
		 */
		public function set q_target_type(value: int): void{
			this._q_target_type = value;
		}
		
		/**
		 * get 拥有BUFF者是否变色（0否，1是）
		 * @return 
		 */
		public function get q_colour(): int{
			return _q_colour;
		}
		
		/**
		 * set 拥有BUFF者是否变色（0否，1是）
		 */
		public function set q_colour(value: int): void{
			this._q_colour = value;
		}
		
		/**
		 * get 是否加成战斗力（1加成，0不加成）
		 * @return 
		 */
		public function get q_fihgt(): int{
			return _q_fihgt;
		}
		
		/**
		 * set 是否加成战斗力（1加成，0不加成）
		 */
		public function set q_fihgt(value: int): void{
			this._q_fihgt = value;
		}
		
	}
}