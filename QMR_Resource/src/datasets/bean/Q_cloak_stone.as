package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_cloak_stone
	 */
	public class Q_cloak_stone extends Bean{
	
		//道具model
		private var _q_item_id: int;
		
		//对应格子(从1开始)
		private var _q_postion: int;
		
		//等级
		private var _q_level: int;
		
		//所需阶数
		private var _q_need_layer: int;
		
		//合成所需数量
		private var _q_num: int;
		
		//下一阶的道具model
		private var _q_next_mode: int;
		
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
		
		//攻速
		private var _q_attack_speed: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具model
			_q_item_id = readInt();
			//对应格子(从1开始)
			_q_postion = readInt();
			//等级
			_q_level = readInt();
			//所需阶数
			_q_need_layer = readInt();
			//合成所需数量
			_q_num = readInt();
			//下一阶的道具model
			_q_next_mode = readInt();
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
			//攻速
			_q_attack_speed = readInt();
			return true;
		}
		
		/**
		 * get 道具model
		 * @return 
		 */
		public function get q_item_id(): int{
			return _q_item_id;
		}
		
		/**
		 * set 道具model
		 */
		public function set q_item_id(value: int): void{
			this._q_item_id = value;
		}
		
		/**
		 * get 对应格子(从1开始)
		 * @return 
		 */
		public function get q_postion(): int{
			return _q_postion;
		}
		
		/**
		 * set 对应格子(从1开始)
		 */
		public function set q_postion(value: int): void{
			this._q_postion = value;
		}
		
		/**
		 * get 等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 所需阶数
		 * @return 
		 */
		public function get q_need_layer(): int{
			return _q_need_layer;
		}
		
		/**
		 * set 所需阶数
		 */
		public function set q_need_layer(value: int): void{
			this._q_need_layer = value;
		}
		
		/**
		 * get 合成所需数量
		 * @return 
		 */
		public function get q_num(): int{
			return _q_num;
		}
		
		/**
		 * set 合成所需数量
		 */
		public function set q_num(value: int): void{
			this._q_num = value;
		}
		
		/**
		 * get 下一阶的道具model
		 * @return 
		 */
		public function get q_next_mode(): int{
			return _q_next_mode;
		}
		
		/**
		 * set 下一阶的道具model
		 */
		public function set q_next_mode(value: int): void{
			this._q_next_mode = value;
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