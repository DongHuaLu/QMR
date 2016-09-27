package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_amulet_skill_add
	 */
	public class Q_amulet_skill_add extends Bean{
	
		//关联物品id
		private var _q_id: String;
		
		//技能ID
		private var _q_skill_id: int;
		
		//已经存在数量
		private var _q_skill_num: int;
		
		//添加概率(万分比)
		private var _q_prob: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关联物品id
			_q_id = readString();
			//技能ID
			_q_skill_id = readInt();
			//已经存在数量
			_q_skill_num = readInt();
			//添加概率(万分比)
			_q_prob = readInt();
			return true;
		}
		
		/**
		 * get 关联物品id
		 * @return 
		 */
		public function get q_id(): String{
			return _q_id;
		}
		
		/**
		 * set 关联物品id
		 */
		public function set q_id(value: String): void{
			this._q_id = value;
		}
		
		/**
		 * get 技能ID
		 * @return 
		 */
		public function get q_skill_id(): int{
			return _q_skill_id;
		}
		
		/**
		 * set 技能ID
		 */
		public function set q_skill_id(value: int): void{
			this._q_skill_id = value;
		}
		
		/**
		 * get 已经存在数量
		 * @return 
		 */
		public function get q_skill_num(): int{
			return _q_skill_num;
		}
		
		/**
		 * set 已经存在数量
		 */
		public function set q_skill_num(value: int): void{
			this._q_skill_num = value;
		}
		
		/**
		 * get 添加概率(万分比)
		 * @return 
		 */
		public function get q_prob(): int{
			return _q_prob;
		}
		
		/**
		 * set 添加概率(万分比)
		 */
		public function set q_prob(value: int): void{
			this._q_prob = value;
		}
		
	}
}