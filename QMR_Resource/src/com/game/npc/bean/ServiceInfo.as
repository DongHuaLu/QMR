package com.game.npc.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * NPC服务信息类
	 */
	public class ServiceInfo extends Bean {
	
		//服务Id
		private var _serviceId: int;
		
		//服务名称
		private var _serviceName: String;
		
		//服务参数
		private var _serviceParameter: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//服务Id
			writeInt(_serviceId);
			//服务名称
			writeString(_serviceName);
			//服务参数
			writeString(_serviceParameter);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//服务Id
			_serviceId = readInt();
			//服务名称
			_serviceName = readString();
			//服务参数
			_serviceParameter = readString();
			return true;
		}
		
		/**
		 * get 服务Id
		 * @return 
		 */
		public function get serviceId(): int{
			return _serviceId;
		}
		
		/**
		 * set 服务Id
		 */
		public function set serviceId(value: int): void{
			this._serviceId = value;
		}
		
		/**
		 * get 服务名称
		 * @return 
		 */
		public function get serviceName(): String{
			return _serviceName;
		}
		
		/**
		 * set 服务名称
		 */
		public function set serviceName(value: String): void{
			this._serviceName = value;
		}
		
		/**
		 * get 服务参数
		 * @return 
		 */
		public function get serviceParameter(): String{
			return _serviceParameter;
		}
		
		/**
		 * set 服务参数
		 */
		public function set serviceParameter(value: String): void{
			this._serviceParameter = value;
		}
		
	}
}