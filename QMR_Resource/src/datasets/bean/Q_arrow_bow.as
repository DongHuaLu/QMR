package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_arrow_bow
	 */
	public class Q_arrow_bow extends Bean{
	
		//箭支ID
		private var _q_bow_id: String;
		
		//箭支名称
		private var _q_bow_name: String;
		
		//主阶数
		private var _q_mainlv: int;
		
		//子阶数
		private var _q_sublv: int;
		
		//箭支换装
		private var _q_swf: int;
		
		//触发技能数
		private var _q_triggerskillnum: int;
		
		//箭支描述
		private var _q_tips: String;
		
		//提升所需道具
		private var _q_need_item: int;
		
		//提升所需道具数量
		private var _q_need_num: int;
		
		//升阶所需弓箭等级
		private var _q_need_arrowlv: int;
		
		//升阶所需等级
		private var _q_need_lv: int;
		
		//攻击附加值
		private var _q_attack: int;
		
		//防御附加值
		private var _q_defence: int;
		
		//闪避附加值
		private var _q_dodge: int;
		
		//爆击附加值
		private var _q_crit: int;
		
		//生命上限附加值
		private var _q_maxhp: int;
		
		//内力上限附加值
		private var _q_maxmp: int;
		
		//体力上限附加值
		private var _q_maxsp: int;
		
		//攻击速度附加值
		private var _q_attackspeed: int;
		
		//移动速度附加值
		private var _q_speed: int;
		
		//幸运附加值
		private var _q_luck: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//箭支ID
			_q_bow_id = readString();
			//箭支名称
			_q_bow_name = readString();
			//主阶数
			_q_mainlv = readInt();
			//子阶数
			_q_sublv = readInt();
			//箭支换装
			_q_swf = readInt();
			//触发技能数
			_q_triggerskillnum = readInt();
			//箭支描述
			_q_tips = readString();
			//提升所需道具
			_q_need_item = readInt();
			//提升所需道具数量
			_q_need_num = readInt();
			//升阶所需弓箭等级
			_q_need_arrowlv = readInt();
			//升阶所需等级
			_q_need_lv = readInt();
			//攻击附加值
			_q_attack = readInt();
			//防御附加值
			_q_defence = readInt();
			//闪避附加值
			_q_dodge = readInt();
			//爆击附加值
			_q_crit = readInt();
			//生命上限附加值
			_q_maxhp = readInt();
			//内力上限附加值
			_q_maxmp = readInt();
			//体力上限附加值
			_q_maxsp = readInt();
			//攻击速度附加值
			_q_attackspeed = readInt();
			//移动速度附加值
			_q_speed = readInt();
			//幸运附加值
			_q_luck = readInt();
			return true;
		}
		
		/**
		 * get 箭支ID
		 * @return 
		 */
		public function get q_bow_id(): String{
			return _q_bow_id;
		}
		
		/**
		 * set 箭支ID
		 */
		public function set q_bow_id(value: String): void{
			this._q_bow_id = value;
		}
		
		/**
		 * get 箭支名称
		 * @return 
		 */
		public function get q_bow_name(): String{
			return _q_bow_name;
		}
		
		/**
		 * set 箭支名称
		 */
		public function set q_bow_name(value: String): void{
			this._q_bow_name = value;
		}
		
		/**
		 * get 主阶数
		 * @return 
		 */
		public function get q_mainlv(): int{
			return _q_mainlv;
		}
		
		/**
		 * set 主阶数
		 */
		public function set q_mainlv(value: int): void{
			this._q_mainlv = value;
		}
		
		/**
		 * get 子阶数
		 * @return 
		 */
		public function get q_sublv(): int{
			return _q_sublv;
		}
		
		/**
		 * set 子阶数
		 */
		public function set q_sublv(value: int): void{
			this._q_sublv = value;
		}
		
		/**
		 * get 箭支换装
		 * @return 
		 */
		public function get q_swf(): int{
			return _q_swf;
		}
		
		/**
		 * set 箭支换装
		 */
		public function set q_swf(value: int): void{
			this._q_swf = value;
		}
		
		/**
		 * get 触发技能数
		 * @return 
		 */
		public function get q_triggerskillnum(): int{
			return _q_triggerskillnum;
		}
		
		/**
		 * set 触发技能数
		 */
		public function set q_triggerskillnum(value: int): void{
			this._q_triggerskillnum = value;
		}
		
		/**
		 * get 箭支描述
		 * @return 
		 */
		public function get q_tips(): String{
			return _q_tips;
		}
		
		/**
		 * set 箭支描述
		 */
		public function set q_tips(value: String): void{
			this._q_tips = value;
		}
		
		/**
		 * get 提升所需道具
		 * @return 
		 */
		public function get q_need_item(): int{
			return _q_need_item;
		}
		
		/**
		 * set 提升所需道具
		 */
		public function set q_need_item(value: int): void{
			this._q_need_item = value;
		}
		
		/**
		 * get 提升所需道具数量
		 * @return 
		 */
		public function get q_need_num(): int{
			return _q_need_num;
		}
		
		/**
		 * set 提升所需道具数量
		 */
		public function set q_need_num(value: int): void{
			this._q_need_num = value;
		}
		
		/**
		 * get 升阶所需弓箭等级
		 * @return 
		 */
		public function get q_need_arrowlv(): int{
			return _q_need_arrowlv;
		}
		
		/**
		 * set 升阶所需弓箭等级
		 */
		public function set q_need_arrowlv(value: int): void{
			this._q_need_arrowlv = value;
		}
		
		/**
		 * get 升阶所需等级
		 * @return 
		 */
		public function get q_need_lv(): int{
			return _q_need_lv;
		}
		
		/**
		 * set 升阶所需等级
		 */
		public function set q_need_lv(value: int): void{
			this._q_need_lv = value;
		}
		
		/**
		 * get 攻击附加值
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击附加值
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 防御附加值
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 防御附加值
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 闪避附加值
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 闪避附加值
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 爆击附加值
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 爆击附加值
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 生命上限附加值
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 生命上限附加值
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 内力上限附加值
		 * @return 
		 */
		public function get q_maxmp(): int{
			return _q_maxmp;
		}
		
		/**
		 * set 内力上限附加值
		 */
		public function set q_maxmp(value: int): void{
			this._q_maxmp = value;
		}
		
		/**
		 * get 体力上限附加值
		 * @return 
		 */
		public function get q_maxsp(): int{
			return _q_maxsp;
		}
		
		/**
		 * set 体力上限附加值
		 */
		public function set q_maxsp(value: int): void{
			this._q_maxsp = value;
		}
		
		/**
		 * get 攻击速度附加值
		 * @return 
		 */
		public function get q_attackspeed(): int{
			return _q_attackspeed;
		}
		
		/**
		 * set 攻击速度附加值
		 */
		public function set q_attackspeed(value: int): void{
			this._q_attackspeed = value;
		}
		
		/**
		 * get 移动速度附加值
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 移动速度附加值
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 幸运附加值
		 * @return 
		 */
		public function get q_luck(): int{
			return _q_luck;
		}
		
		/**
		 * set 幸运附加值
		 */
		public function set q_luck(value: int): void{
			this._q_luck = value;
		}
		
	}
}