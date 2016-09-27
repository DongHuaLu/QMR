package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_qingfengguyun
	 */
	public class Q_qingfengguyun extends Bean{
	
		//诗词编号
		private var _q_id: int;
		
		//诗词名句
		private var _q_shichi: String;
		
		//刷新波次min
		private var _q_min_refresh: int;
		
		//属性波次max
		private var _q_max_refresh: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//诗词编号
			_q_id = readInt();
			//诗词名句
			_q_shichi = readString();
			//刷新波次min
			_q_min_refresh = readInt();
			//属性波次max
			_q_max_refresh = readInt();
			return true;
		}
		
		/**
		 * get 诗词编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 诗词编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 诗词名句
		 * @return 
		 */
		public function get q_shichi(): String{
			return _q_shichi;
		}
		
		/**
		 * set 诗词名句
		 */
		public function set q_shichi(value: String): void{
			this._q_shichi = value;
		}
		
		/**
		 * get 刷新波次min
		 * @return 
		 */
		public function get q_min_refresh(): int{
			return _q_min_refresh;
		}
		
		/**
		 * set 刷新波次min
		 */
		public function set q_min_refresh(value: int): void{
			this._q_min_refresh = value;
		}
		
		/**
		 * get 属性波次max
		 * @return 
		 */
		public function get q_max_refresh(): int{
			return _q_max_refresh;
		}
		
		/**
		 * set 属性波次max
		 */
		public function set q_max_refresh(value: int): void{
			this._q_max_refresh = value;
		}
		
	}
}