package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_fightspirit
	 */
	public class Q_fightspirit extends Bean{
	
		//战魂id
		private var _q_id: String;
		
		//搜索次数
		private var _q_fightspirit_num: int;
		
		//战魂得到类型
		private var _q_fightspirit_type: int;
		
		//默认获得战魂数
		private var _q_getfightspirit: int;
		
		//默认所需元宝数
		private var _q_yuanbao: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//战魂id
			_q_id = readString();
			//搜索次数
			_q_fightspirit_num = readInt();
			//战魂得到类型
			_q_fightspirit_type = readInt();
			//默认获得战魂数
			_q_getfightspirit = readInt();
			//默认所需元宝数
			_q_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get 战魂id
		 * @return 
		 */
		public function get q_id(): String{
			return _q_id;
		}
		
		/**
		 * set 战魂id
		 */
		public function set q_id(value: String): void{
			this._q_id = value;
		}
		
		/**
		 * get 搜索次数
		 * @return 
		 */
		public function get q_fightspirit_num(): int{
			return _q_fightspirit_num;
		}
		
		/**
		 * set 搜索次数
		 */
		public function set q_fightspirit_num(value: int): void{
			this._q_fightspirit_num = value;
		}
		
		/**
		 * get 战魂得到类型
		 * @return 
		 */
		public function get q_fightspirit_type(): int{
			return _q_fightspirit_type;
		}
		
		/**
		 * set 战魂得到类型
		 */
		public function set q_fightspirit_type(value: int): void{
			this._q_fightspirit_type = value;
		}
		
		/**
		 * get 默认获得战魂数
		 * @return 
		 */
		public function get q_getfightspirit(): int{
			return _q_getfightspirit;
		}
		
		/**
		 * set 默认获得战魂数
		 */
		public function set q_getfightspirit(value: int): void{
			this._q_getfightspirit = value;
		}
		
		/**
		 * get 默认所需元宝数
		 * @return 
		 */
		public function get q_yuanbao(): int{
			return _q_yuanbao;
		}
		
		/**
		 * set 默认所需元宝数
		 */
		public function set q_yuanbao(value: int): void{
			this._q_yuanbao = value;
		}
		
	}
}