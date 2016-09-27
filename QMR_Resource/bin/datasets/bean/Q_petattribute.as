package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_petattribute
	 */
	public class Q_petattribute extends Bean{
	
		//ID
		private var _q_id: String;
		
		//模型
		private var _q_modelid: int;
		
		//侍宠名称
		private var _q_name: String;
		
		//侍宠等级
		private var _q_level: int;
		
		//生命
		private var _q_maxhp: int;
		
		//攻击
		private var _q_attack: int;
		
		//攻击速度
		private var _q_attack_speed: int;
		
		//暴击
		private var _q_crit: int;
		
		//闪避
		private var _q_dodge: int;
		
		//侍宠打坐恢复血量
		private var _q_dz_recover_hp: int;
		
		//自动回血（10秒一次）
		private var _q_recover_hp: int;
		
		//移动速度
		private var _q_movespeed: int;
		
		//拥有主人战斗力万分比
		private var _q_fight_percent: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//ID
			_q_id = readString();
			//模型
			_q_modelid = readInt();
			//侍宠名称
			_q_name = readString();
			//侍宠等级
			_q_level = readInt();
			//生命
			_q_maxhp = readInt();
			//攻击
			_q_attack = readInt();
			//攻击速度
			_q_attack_speed = readInt();
			//暴击
			_q_crit = readInt();
			//闪避
			_q_dodge = readInt();
			//侍宠打坐恢复血量
			_q_dz_recover_hp = readInt();
			//自动回血（10秒一次）
			_q_recover_hp = readInt();
			//移动速度
			_q_movespeed = readInt();
			//拥有主人战斗力万分比
			_q_fight_percent = readInt();
			return true;
		}
		
		/**
		 * get ID
		 * @return 
		 */
		public function get q_id(): String{
			return _q_id;
		}
		
		/**
		 * set ID
		 */
		public function set q_id(value: String): void{
			this._q_id = value;
		}
		
		/**
		 * get 模型
		 * @return 
		 */
		public function get q_modelid(): int{
			return _q_modelid;
		}
		
		/**
		 * set 模型
		 */
		public function set q_modelid(value: int): void{
			this._q_modelid = value;
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
		 * get 侍宠等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 侍宠等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 生命
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 生命
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 攻击
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
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
		 * get 暴击
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 暴击
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 闪避
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 闪避
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 侍宠打坐恢复血量
		 * @return 
		 */
		public function get q_dz_recover_hp(): int{
			return _q_dz_recover_hp;
		}
		
		/**
		 * set 侍宠打坐恢复血量
		 */
		public function set q_dz_recover_hp(value: int): void{
			this._q_dz_recover_hp = value;
		}
		
		/**
		 * get 自动回血（10秒一次）
		 * @return 
		 */
		public function get q_recover_hp(): int{
			return _q_recover_hp;
		}
		
		/**
		 * set 自动回血（10秒一次）
		 */
		public function set q_recover_hp(value: int): void{
			this._q_recover_hp = value;
		}
		
		/**
		 * get 移动速度
		 * @return 
		 */
		public function get q_movespeed(): int{
			return _q_movespeed;
		}
		
		/**
		 * set 移动速度
		 */
		public function set q_movespeed(value: int): void{
			this._q_movespeed = value;
		}
		
		/**
		 * get 拥有主人战斗力万分比
		 * @return 
		 */
		public function get q_fight_percent(): int{
			return _q_fight_percent;
		}
		
		/**
		 * set 拥有主人战斗力万分比
		 */
		public function set q_fight_percent(value: int): void{
			this._q_fight_percent = value;
		}
		
	}
}