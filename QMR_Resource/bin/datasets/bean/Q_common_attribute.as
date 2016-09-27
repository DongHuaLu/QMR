package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_common_attribute
	 */
	public class Q_common_attribute extends Bean{
	
		//编号
		private var _q_id: int;
		
		//类型(1:披风)
		private var _q_type: int;
		
		//生命加成
		private var _q_hp: int;
		
		//攻击
		private var _q_attack: int;
		
		//防御
		private var _q_defence: int;
		
		//暴击
		private var _q_crit: int;
		
		//闪避
		private var _q_dodge: int;
		
		//内力
		private var _q_mp: int;
		
		//体力
		private var _q_sp: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//编号
			_q_id = readInt();
			//类型(1:披风)
			_q_type = readInt();
			//生命加成
			_q_hp = readInt();
			//攻击
			_q_attack = readInt();
			//防御
			_q_defence = readInt();
			//暴击
			_q_crit = readInt();
			//闪避
			_q_dodge = readInt();
			//内力
			_q_mp = readInt();
			//体力
			_q_sp = readInt();
			return true;
		}
		
		/**
		 * get 编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 类型(1:披风)
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 类型(1:披风)
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 生命加成
		 * @return 
		 */
		public function get q_hp(): int{
			return _q_hp;
		}
		
		/**
		 * set 生命加成
		 */
		public function set q_hp(value: int): void{
			this._q_hp = value;
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
		
	}
}