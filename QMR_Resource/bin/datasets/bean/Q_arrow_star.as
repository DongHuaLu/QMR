package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_arrow_star
	 */
	public class Q_arrow_star extends Bean{
	
		//星斗ID
		private var _q_star_id: String;
		
		//星斗名
		private var _q_star_name: String;
		
		//主阶数
		private var _q_mainlv: int;
		
		//子阶数
		private var _q_sublv: int;
		
		//最大阶数
		private var _q_maxlv: int;
		
		//tips描述
		private var _q_tips: String;
		
		//附带技能(技能id_技能等级)
		private var _q_ownskill: String;
		
		//升阶所需战魂类型
		private var _q_needtype: int;
		
		//升阶所需战魂值
		private var _q_neednum: int;
		
		//未激活星斗swf资源
		private var _q_noswf: int;
		
		//已激活星斗swf资源
		private var _q_useswf: int;
		
		//点击星斗后swf造型
		private var _q_clickswf: int;
		
		//激活弓箭
		private var _q_active_arrowid: int;
		
		//升阶所需等级
		private var _q_need_lv: int;
		
		//tips图标25*25
		private var _q_tips25: int;
		
		//tips图标30*30
		private var _q_tips30: int;
		
		//tips图标60*60
		private var _q_tips60: int;
		
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
			//星斗ID
			_q_star_id = readString();
			//星斗名
			_q_star_name = readString();
			//主阶数
			_q_mainlv = readInt();
			//子阶数
			_q_sublv = readInt();
			//最大阶数
			_q_maxlv = readInt();
			//tips描述
			_q_tips = readString();
			//附带技能(技能id_技能等级)
			_q_ownskill = readString();
			//升阶所需战魂类型
			_q_needtype = readInt();
			//升阶所需战魂值
			_q_neednum = readInt();
			//未激活星斗swf资源
			_q_noswf = readInt();
			//已激活星斗swf资源
			_q_useswf = readInt();
			//点击星斗后swf造型
			_q_clickswf = readInt();
			//激活弓箭
			_q_active_arrowid = readInt();
			//升阶所需等级
			_q_need_lv = readInt();
			//tips图标25*25
			_q_tips25 = readInt();
			//tips图标30*30
			_q_tips30 = readInt();
			//tips图标60*60
			_q_tips60 = readInt();
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
		 * get 星斗ID
		 * @return 
		 */
		public function get q_star_id(): String{
			return _q_star_id;
		}
		
		/**
		 * set 星斗ID
		 */
		public function set q_star_id(value: String): void{
			this._q_star_id = value;
		}
		
		/**
		 * get 星斗名
		 * @return 
		 */
		public function get q_star_name(): String{
			return _q_star_name;
		}
		
		/**
		 * set 星斗名
		 */
		public function set q_star_name(value: String): void{
			this._q_star_name = value;
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
		 * get 最大阶数
		 * @return 
		 */
		public function get q_maxlv(): int{
			return _q_maxlv;
		}
		
		/**
		 * set 最大阶数
		 */
		public function set q_maxlv(value: int): void{
			this._q_maxlv = value;
		}
		
		/**
		 * get tips描述
		 * @return 
		 */
		public function get q_tips(): String{
			return _q_tips;
		}
		
		/**
		 * set tips描述
		 */
		public function set q_tips(value: String): void{
			this._q_tips = value;
		}
		
		/**
		 * get 附带技能(技能id_技能等级)
		 * @return 
		 */
		public function get q_ownskill(): String{
			return _q_ownskill;
		}
		
		/**
		 * set 附带技能(技能id_技能等级)
		 */
		public function set q_ownskill(value: String): void{
			this._q_ownskill = value;
		}
		
		/**
		 * get 升阶所需战魂类型
		 * @return 
		 */
		public function get q_needtype(): int{
			return _q_needtype;
		}
		
		/**
		 * set 升阶所需战魂类型
		 */
		public function set q_needtype(value: int): void{
			this._q_needtype = value;
		}
		
		/**
		 * get 升阶所需战魂值
		 * @return 
		 */
		public function get q_neednum(): int{
			return _q_neednum;
		}
		
		/**
		 * set 升阶所需战魂值
		 */
		public function set q_neednum(value: int): void{
			this._q_neednum = value;
		}
		
		/**
		 * get 未激活星斗swf资源
		 * @return 
		 */
		public function get q_noswf(): int{
			return _q_noswf;
		}
		
		/**
		 * set 未激活星斗swf资源
		 */
		public function set q_noswf(value: int): void{
			this._q_noswf = value;
		}
		
		/**
		 * get 已激活星斗swf资源
		 * @return 
		 */
		public function get q_useswf(): int{
			return _q_useswf;
		}
		
		/**
		 * set 已激活星斗swf资源
		 */
		public function set q_useswf(value: int): void{
			this._q_useswf = value;
		}
		
		/**
		 * get 点击星斗后swf造型
		 * @return 
		 */
		public function get q_clickswf(): int{
			return _q_clickswf;
		}
		
		/**
		 * set 点击星斗后swf造型
		 */
		public function set q_clickswf(value: int): void{
			this._q_clickswf = value;
		}
		
		/**
		 * get 激活弓箭
		 * @return 
		 */
		public function get q_active_arrowid(): int{
			return _q_active_arrowid;
		}
		
		/**
		 * set 激活弓箭
		 */
		public function set q_active_arrowid(value: int): void{
			this._q_active_arrowid = value;
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
		 * get tips图标25*25
		 * @return 
		 */
		public function get q_tips25(): int{
			return _q_tips25;
		}
		
		/**
		 * set tips图标25*25
		 */
		public function set q_tips25(value: int): void{
			this._q_tips25 = value;
		}
		
		/**
		 * get tips图标30*30
		 * @return 
		 */
		public function get q_tips30(): int{
			return _q_tips30;
		}
		
		/**
		 * set tips图标30*30
		 */
		public function set q_tips30(value: int): void{
			this._q_tips30 = value;
		}
		
		/**
		 * get tips图标60*60
		 * @return 
		 */
		public function get q_tips60(): int{
			return _q_tips60;
		}
		
		/**
		 * set tips图标60*60
		 */
		public function set q_tips60(value: int): void{
			this._q_tips60 = value;
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