package com.game.emperorcity.message{
	import com.game.emperorcity.bean.EmperorWarInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 地图广播皇城战即时消息
	 */
	public class ResEmperorWarInfoToClientMessage extends Message {
	
		//皇城战即时消息
		private var _emperorWarInfo: EmperorWarInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//皇城战即时消息
			writeBean(_emperorWarInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//皇城战即时消息
			_emperorWarInfo = readBean(EmperorWarInfo) as EmperorWarInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169107;
		}
		
		/**
		 * get 皇城战即时消息
		 * @return 
		 */
		public function get emperorWarInfo(): EmperorWarInfo{
			return _emperorWarInfo;
		}
		
		/**
		 * set 皇城战即时消息
		 */
		public function set emperorWarInfo(value: EmperorWarInfo): void{
			this._emperorWarInfo = value;
		}
		
	}
}