package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_horseweapon_attr
	 */
	public class Q_horseweapon_attr extends Bean{
	
		//骑兵编号=骑兵阶数_等级
		private var _q_id: String;
		
		//骑兵阶数
		private var _q_rank: int;
		
		//骑兵等级
		private var _q_level: int;
		
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
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//骑兵编号=骑兵阶数_等级
			_q_id = readString();
			//骑兵阶数
			_q_rank = readInt();
			//骑兵等级
			_q_level = readInt();
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
			return true;
		}
		
		/**
		 * get 骑兵编号=骑兵阶数_等级
		 * @return 
		 */
		public function get q_id(): String{
			return _q_id;
		}
		
		/**
		 * set 骑兵编号=骑兵阶数_等级
		 */
		public function set q_id(value: String): void{
			this._q_id = value;
		}
		
		/**
		 * get 骑兵阶数
		 * @return 
		 */
		public function get q_rank(): int{
			return _q_rank;
		}
		
		/**
		 * set 骑兵阶数
		 */
		public function set q_rank(value: int): void{
			this._q_rank = value;
		}
		
		/**
		 * get 骑兵等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 骑兵等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
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
		
	}
}