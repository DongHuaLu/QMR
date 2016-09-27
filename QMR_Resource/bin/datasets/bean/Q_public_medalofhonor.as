package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_public_medalofhonor
	 */
	public class Q_public_medalofhonor extends Bean{
	
		//勋章ID
		private var _q_id: int;
		
		//勋章名称
		private var _q_name: String;
		
		//勋章阶数
		private var _q_rank: int;
		
		//勋章造型路径
		private var _q_rank_path: String;
		
		//攻击
		private var _q_attack: int;
		
		//防御
		private var _q_defence: int;
		
		//暴击
		private var _q_critical: int;
		
		//闪避
		private var _q_doge: int;
		
		//血量
		private var _q_hp: int;
		
		//内力
		private var _q_mp: int;
		
		//体力
		private var _q_sp: int;
		
		//攻速
		private var _q_attack_speed: int;
		
		//移速
		private var _q_move_speed: int;
		
		//升级需要荣誉
		private var _q_need_honor: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//勋章ID
			_q_id = readInt();
			//勋章名称
			_q_name = readString();
			//勋章阶数
			_q_rank = readInt();
			//勋章造型路径
			_q_rank_path = readString();
			//攻击
			_q_attack = readInt();
			//防御
			_q_defence = readInt();
			//暴击
			_q_critical = readInt();
			//闪避
			_q_doge = readInt();
			//血量
			_q_hp = readInt();
			//内力
			_q_mp = readInt();
			//体力
			_q_sp = readInt();
			//攻速
			_q_attack_speed = readInt();
			//移速
			_q_move_speed = readInt();
			//升级需要荣誉
			_q_need_honor = readInt();
			return true;
		}
		
		/**
		 * get 勋章ID
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 勋章ID
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 勋章名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 勋章名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 勋章阶数
		 * @return 
		 */
		public function get q_rank(): int{
			return _q_rank;
		}
		
		/**
		 * set 勋章阶数
		 */
		public function set q_rank(value: int): void{
			this._q_rank = value;
		}
		
		/**
		 * get 勋章造型路径
		 * @return 
		 */
		public function get q_rank_path(): String{
			return _q_rank_path;
		}
		
		/**
		 * set 勋章造型路径
		 */
		public function set q_rank_path(value: String): void{
			this._q_rank_path = value;
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
		 * get 防御
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 防御
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 暴击
		 * @return 
		 */
		public function get q_critical(): int{
			return _q_critical;
		}
		
		/**
		 * set 暴击
		 */
		public function set q_critical(value: int): void{
			this._q_critical = value;
		}
		
		/**
		 * get 闪避
		 * @return 
		 */
		public function get q_doge(): int{
			return _q_doge;
		}
		
		/**
		 * set 闪避
		 */
		public function set q_doge(value: int): void{
			this._q_doge = value;
		}
		
		/**
		 * get 血量
		 * @return 
		 */
		public function get q_hp(): int{
			return _q_hp;
		}
		
		/**
		 * set 血量
		 */
		public function set q_hp(value: int): void{
			this._q_hp = value;
		}
		
		/**
		 * get 内力
		 * @return 
		 */
		public function get q_mp(): int{
			return _q_mp;
		}
		
		/**
		 * set 内力
		 */
		public function set q_mp(value: int): void{
			this._q_mp = value;
		}
		
		/**
		 * get 体力
		 * @return 
		 */
		public function get q_sp(): int{
			return _q_sp;
		}
		
		/**
		 * set 体力
		 */
		public function set q_sp(value: int): void{
			this._q_sp = value;
		}
		
		/**
		 * get 攻速
		 * @return 
		 */
		public function get q_attack_speed(): int{
			return _q_attack_speed;
		}
		
		/**
		 * set 攻速
		 */
		public function set q_attack_speed(value: int): void{
			this._q_attack_speed = value;
		}
		
		/**
		 * get 移速
		 * @return 
		 */
		public function get q_move_speed(): int{
			return _q_move_speed;
		}
		
		/**
		 * set 移速
		 */
		public function set q_move_speed(value: int): void{
			this._q_move_speed = value;
		}
		
		/**
		 * get 升级需要荣誉
		 * @return 
		 */
		public function get q_need_honor(): int{
			return _q_need_honor;
		}
		
		/**
		 * set 升级需要荣誉
		 */
		public function set q_need_honor(value: int): void{
			this._q_need_honor = value;
		}
		
	}
}