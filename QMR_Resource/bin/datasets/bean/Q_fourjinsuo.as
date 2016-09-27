package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_fourjinsuo
	 */
	public class Q_fourjinsuo extends Bean{
	
		//四门金锁阵波次
		private var _q_id: int;
		
		//本波刷新怪物数量
		private var _q_num: int;
		
		//本波小怪出现间隔时间（毫秒）
		private var _q_mobs_interval_num: int;
		
		//本波小怪移动速度
		private var _q_move_speed: int;
		
		//本波怪物等级加成参数
		private var _q_mon_level_add: int;
		
		//本波守将等级加成参数
		private var _q_morimasa_level_add: int;
		
		//波次刷新间隔时间（单位：秒）
		private var _q_interval_num: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//四门金锁阵波次
			_q_id = readInt();
			//本波刷新怪物数量
			_q_num = readInt();
			//本波小怪出现间隔时间（毫秒）
			_q_mobs_interval_num = readInt();
			//本波小怪移动速度
			_q_move_speed = readInt();
			//本波怪物等级加成参数
			_q_mon_level_add = readInt();
			//本波守将等级加成参数
			_q_morimasa_level_add = readInt();
			//波次刷新间隔时间（单位：秒）
			_q_interval_num = readInt();
			return true;
		}
		
		/**
		 * get 四门金锁阵波次
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 四门金锁阵波次
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 本波刷新怪物数量
		 * @return 
		 */
		public function get q_num(): int{
			return _q_num;
		}
		
		/**
		 * set 本波刷新怪物数量
		 */
		public function set q_num(value: int): void{
			this._q_num = value;
		}
		
		/**
		 * get 本波小怪出现间隔时间（毫秒）
		 * @return 
		 */
		public function get q_mobs_interval_num(): int{
			return _q_mobs_interval_num;
		}
		
		/**
		 * set 本波小怪出现间隔时间（毫秒）
		 */
		public function set q_mobs_interval_num(value: int): void{
			this._q_mobs_interval_num = value;
		}
		
		/**
		 * get 本波小怪移动速度
		 * @return 
		 */
		public function get q_move_speed(): int{
			return _q_move_speed;
		}
		
		/**
		 * set 本波小怪移动速度
		 */
		public function set q_move_speed(value: int): void{
			this._q_move_speed = value;
		}
		
		/**
		 * get 本波怪物等级加成参数
		 * @return 
		 */
		public function get q_mon_level_add(): int{
			return _q_mon_level_add;
		}
		
		/**
		 * set 本波怪物等级加成参数
		 */
		public function set q_mon_level_add(value: int): void{
			this._q_mon_level_add = value;
		}
		
		/**
		 * get 本波守将等级加成参数
		 * @return 
		 */
		public function get q_morimasa_level_add(): int{
			return _q_morimasa_level_add;
		}
		
		/**
		 * set 本波守将等级加成参数
		 */
		public function set q_morimasa_level_add(value: int): void{
			this._q_morimasa_level_add = value;
		}
		
		/**
		 * get 波次刷新间隔时间（单位：秒）
		 * @return 
		 */
		public function get q_interval_num(): int{
			return _q_interval_num;
		}
		
		/**
		 * set 波次刷新间隔时间（单位：秒）
		 */
		public function set q_interval_num(value: int): void{
			this._q_interval_num = value;
		}
		
	}
}