package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_hiddenweapon_skill
	 */
	public class Q_hiddenweapon_skill extends Bean{
	
		//技能ID
		private var _q_skill: String;
		
		//所需暗器阶数
		private var _q_need_level: int;
		
		//技能书减少投掷次数
		private var _q_reduce_times: int;
		
		//技能类型(0:非攻击次数触发 1:攻击次数触发)
		private var _q_type: int;
		
		//替换互斥概率（暂时不用）
		private var _q_prob: int;
		
		//关联物品id
		private var _q_item_id: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能ID
			_q_skill = readString();
			//所需暗器阶数
			_q_need_level = readInt();
			//技能书减少投掷次数
			_q_reduce_times = readInt();
			//技能类型(0:非攻击次数触发 1:攻击次数触发)
			_q_type = readInt();
			//替换互斥概率（暂时不用）
			_q_prob = readInt();
			//关联物品id
			_q_item_id = readInt();
			return true;
		}
		
		/**
		 * get 技能ID
		 * @return 
		 */
		public function get q_skill(): String{
			return _q_skill;
		}
		
		/**
		 * set 技能ID
		 */
		public function set q_skill(value: String): void{
			this._q_skill = value;
		}
		
		/**
		 * get 所需暗器阶数
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 所需暗器阶数
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 技能书减少投掷次数
		 * @return 
		 */
		public function get q_reduce_times(): int{
			return _q_reduce_times;
		}
		
		/**
		 * set 技能书减少投掷次数
		 */
		public function set q_reduce_times(value: int): void{
			this._q_reduce_times = value;
		}
		
		/**
		 * get 技能类型(0:非攻击次数触发 1:攻击次数触发)
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 技能类型(0:非攻击次数触发 1:攻击次数触发)
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 替换互斥概率（暂时不用）
		 * @return 
		 */
		public function get q_prob(): int{
			return _q_prob;
		}
		
		/**
		 * set 替换互斥概率（暂时不用）
		 */
		public function set q_prob(value: int): void{
			this._q_prob = value;
		}
		
		/**
		 * get 关联物品id
		 * @return 
		 */
		public function get q_item_id(): int{
			return _q_item_id;
		}
		
		/**
		 * set 关联物品id
		 */
		public function set q_item_id(value: int): void{
			this._q_item_id = value;
		}
		
	}
}