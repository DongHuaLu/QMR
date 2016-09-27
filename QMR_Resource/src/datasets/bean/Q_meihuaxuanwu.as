package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_meihuaxuanwu
	 */
	public class Q_meihuaxuanwu extends Bean{
	
		//阵形编号
		private var _q_id: int;
		
		//阵形分布(红,蓝,绿,黄)
		private var _q_zhenxing: String;
		
		//通关梅花桩所需数量上限
		private var _q_limitneedhit: int;
		
		//通关破桩阶段所需时间上限(单位：秒)
		private var _q_limitneedtime: int;
		
		//每次破桩减少BOSS血量
		private var _q_decbosshp: int;
		
		//副本追踪面板提示描述信息（支持HTML）
		private var _q_desc: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//阵形编号
			_q_id = readInt();
			//阵形分布(红,蓝,绿,黄)
			_q_zhenxing = readString();
			//通关梅花桩所需数量上限
			_q_limitneedhit = readInt();
			//通关破桩阶段所需时间上限(单位：秒)
			_q_limitneedtime = readInt();
			//每次破桩减少BOSS血量
			_q_decbosshp = readInt();
			//副本追踪面板提示描述信息（支持HTML）
			_q_desc = readString();
			return true;
		}
		
		/**
		 * get 阵形编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 阵形编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 阵形分布(红,蓝,绿,黄)
		 * @return 
		 */
		public function get q_zhenxing(): String{
			return _q_zhenxing;
		}
		
		/**
		 * set 阵形分布(红,蓝,绿,黄)
		 */
		public function set q_zhenxing(value: String): void{
			this._q_zhenxing = value;
		}
		
		/**
		 * get 通关梅花桩所需数量上限
		 * @return 
		 */
		public function get q_limitneedhit(): int{
			return _q_limitneedhit;
		}
		
		/**
		 * set 通关梅花桩所需数量上限
		 */
		public function set q_limitneedhit(value: int): void{
			this._q_limitneedhit = value;
		}
		
		/**
		 * get 通关破桩阶段所需时间上限(单位：秒)
		 * @return 
		 */
		public function get q_limitneedtime(): int{
			return _q_limitneedtime;
		}
		
		/**
		 * set 通关破桩阶段所需时间上限(单位：秒)
		 */
		public function set q_limitneedtime(value: int): void{
			this._q_limitneedtime = value;
		}
		
		/**
		 * get 每次破桩减少BOSS血量
		 * @return 
		 */
		public function get q_decbosshp(): int{
			return _q_decbosshp;
		}
		
		/**
		 * set 每次破桩减少BOSS血量
		 */
		public function set q_decbosshp(value: int): void{
			this._q_decbosshp = value;
		}
		
		/**
		 * get 副本追踪面板提示描述信息（支持HTML）
		 * @return 
		 */
		public function get q_desc(): String{
			return _q_desc;
		}
		
		/**
		 * set 副本追踪面板提示描述信息（支持HTML）
		 */
		public function set q_desc(value: String): void{
			this._q_desc = value;
		}
		
	}
}