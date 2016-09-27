package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_hiddenweapon_attr
	 */
	public class Q_hiddenweapon_attr extends Bean{
	
		//暗器编号=暗器阶数
		private var _q_id: int;
		
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
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//暗器编号=暗器阶数
			_q_id = readInt();
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
			return true;
		}
		
		/**
		 * get 暗器编号=暗器阶数
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 暗器编号=暗器阶数
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
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
		
	}
}