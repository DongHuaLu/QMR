package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_jiaochang
	 */
	public class Q_jiaochang extends Bean{
	
		//石台编号
		private var _q_id: int;
		
		//变化间隔时间1
		private var _q_change_time1: int;
		
		//变化间隔时间2
		private var _q_change_time2: int;
		
		//变化间隔时间3
		private var _q_change_time3: int;
		
		//变红球几率
		private var _q_redball: int;
		
		//变绿球几率
		private var _q_greenball: int;
		
		//变紫球几率
		private var _q_purpleball: int;
		
		//积分乘2几率
		private var _q_double: int;
		
		//积分除2几率
		private var _q_half: int;
		
		//球复活时间缩短几率
		private var _q_refresh_del: int;
		
		//球复活时间缩短秒数
		private var _q_refresh_del_time: int;
		
		//球变化时间延长几率
		private var _q_extend: int;
		
		//球变化时间延长秒数
		private var _q_extend_time: int;
		
		//被吃后复活时间
		private var _q_refresh_time: int;
		
		//刷新坐标X
		private var _q_x: int;
		
		//刷新坐标Y
		private var _q_y: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//石台编号
			_q_id = readInt();
			//变化间隔时间1
			_q_change_time1 = readInt();
			//变化间隔时间2
			_q_change_time2 = readInt();
			//变化间隔时间3
			_q_change_time3 = readInt();
			//变红球几率
			_q_redball = readInt();
			//变绿球几率
			_q_greenball = readInt();
			//变紫球几率
			_q_purpleball = readInt();
			//积分乘2几率
			_q_double = readInt();
			//积分除2几率
			_q_half = readInt();
			//球复活时间缩短几率
			_q_refresh_del = readInt();
			//球复活时间缩短秒数
			_q_refresh_del_time = readInt();
			//球变化时间延长几率
			_q_extend = readInt();
			//球变化时间延长秒数
			_q_extend_time = readInt();
			//被吃后复活时间
			_q_refresh_time = readInt();
			//刷新坐标X
			_q_x = readInt();
			//刷新坐标Y
			_q_y = readInt();
			return true;
		}
		
		/**
		 * get 石台编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 石台编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 变化间隔时间1
		 * @return 
		 */
		public function get q_change_time1(): int{
			return _q_change_time1;
		}
		
		/**
		 * set 变化间隔时间1
		 */
		public function set q_change_time1(value: int): void{
			this._q_change_time1 = value;
		}
		
		/**
		 * get 变化间隔时间2
		 * @return 
		 */
		public function get q_change_time2(): int{
			return _q_change_time2;
		}
		
		/**
		 * set 变化间隔时间2
		 */
		public function set q_change_time2(value: int): void{
			this._q_change_time2 = value;
		}
		
		/**
		 * get 变化间隔时间3
		 * @return 
		 */
		public function get q_change_time3(): int{
			return _q_change_time3;
		}
		
		/**
		 * set 变化间隔时间3
		 */
		public function set q_change_time3(value: int): void{
			this._q_change_time3 = value;
		}
		
		/**
		 * get 变红球几率
		 * @return 
		 */
		public function get q_redball(): int{
			return _q_redball;
		}
		
		/**
		 * set 变红球几率
		 */
		public function set q_redball(value: int): void{
			this._q_redball = value;
		}
		
		/**
		 * get 变绿球几率
		 * @return 
		 */
		public function get q_greenball(): int{
			return _q_greenball;
		}
		
		/**
		 * set 变绿球几率
		 */
		public function set q_greenball(value: int): void{
			this._q_greenball = value;
		}
		
		/**
		 * get 变紫球几率
		 * @return 
		 */
		public function get q_purpleball(): int{
			return _q_purpleball;
		}
		
		/**
		 * set 变紫球几率
		 */
		public function set q_purpleball(value: int): void{
			this._q_purpleball = value;
		}
		
		/**
		 * get 积分乘2几率
		 * @return 
		 */
		public function get q_double(): int{
			return _q_double;
		}
		
		/**
		 * set 积分乘2几率
		 */
		public function set q_double(value: int): void{
			this._q_double = value;
		}
		
		/**
		 * get 积分除2几率
		 * @return 
		 */
		public function get q_half(): int{
			return _q_half;
		}
		
		/**
		 * set 积分除2几率
		 */
		public function set q_half(value: int): void{
			this._q_half = value;
		}
		
		/**
		 * get 球复活时间缩短几率
		 * @return 
		 */
		public function get q_refresh_del(): int{
			return _q_refresh_del;
		}
		
		/**
		 * set 球复活时间缩短几率
		 */
		public function set q_refresh_del(value: int): void{
			this._q_refresh_del = value;
		}
		
		/**
		 * get 球复活时间缩短秒数
		 * @return 
		 */
		public function get q_refresh_del_time(): int{
			return _q_refresh_del_time;
		}
		
		/**
		 * set 球复活时间缩短秒数
		 */
		public function set q_refresh_del_time(value: int): void{
			this._q_refresh_del_time = value;
		}
		
		/**
		 * get 球变化时间延长几率
		 * @return 
		 */
		public function get q_extend(): int{
			return _q_extend;
		}
		
		/**
		 * set 球变化时间延长几率
		 */
		public function set q_extend(value: int): void{
			this._q_extend = value;
		}
		
		/**
		 * get 球变化时间延长秒数
		 * @return 
		 */
		public function get q_extend_time(): int{
			return _q_extend_time;
		}
		
		/**
		 * set 球变化时间延长秒数
		 */
		public function set q_extend_time(value: int): void{
			this._q_extend_time = value;
		}
		
		/**
		 * get 被吃后复活时间
		 * @return 
		 */
		public function get q_refresh_time(): int{
			return _q_refresh_time;
		}
		
		/**
		 * set 被吃后复活时间
		 */
		public function set q_refresh_time(value: int): void{
			this._q_refresh_time = value;
		}
		
		/**
		 * get 刷新坐标X
		 * @return 
		 */
		public function get q_x(): int{
			return _q_x;
		}
		
		/**
		 * set 刷新坐标X
		 */
		public function set q_x(value: int): void{
			this._q_x = value;
		}
		
		/**
		 * get 刷新坐标Y
		 * @return 
		 */
		public function get q_y(): int{
			return _q_y;
		}
		
		/**
		 * set 刷新坐标Y
		 */
		public function set q_y(value: int): void{
			this._q_y = value;
		}
		
	}
}