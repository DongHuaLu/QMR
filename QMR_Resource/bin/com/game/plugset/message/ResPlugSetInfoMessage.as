package com.game.plugset.message{
	import com.game.plugset.bean.PlugSetInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送自动挂机设置内容
	 */
	public class ResPlugSetInfoMessage extends Message {
	
		//自动挂机设置内容
		private var _plugsetinfo: PlugSetInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//自动挂机设置内容
			writeBean(_plugsetinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//自动挂机设置内容
			_plugsetinfo = readBean(PlugSetInfo) as PlugSetInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 131101;
		}
		
		/**
		 * get 自动挂机设置内容
		 * @return 
		 */
		public function get plugsetinfo(): PlugSetInfo{
			return _plugsetinfo;
		}
		
		/**
		 * set 自动挂机设置内容
		 */
		public function set plugsetinfo(value: PlugSetInfo): void{
			this._plugsetinfo = value;
		}
		
	}
}