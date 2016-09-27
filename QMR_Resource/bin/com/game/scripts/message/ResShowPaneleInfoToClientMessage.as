package com.game.scripts.message{
	import com.game.scripts.bean.PanelInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送前端面板信息
	 */
	public class ResShowPaneleInfoToClientMessage extends Message {
	
		//面板信息
		private var _panelInfo: PanelInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板信息
			writeBean(_panelInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板信息
			_panelInfo = readBean(PanelInfo) as PanelInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 148102;
		}
		
		/**
		 * get 面板信息
		 * @return 
		 */
		public function get panelInfo(): PanelInfo{
			return _panelInfo;
		}
		
		/**
		 * set 面板信息
		 */
		public function set panelInfo(value: PanelInfo): void{
			this._panelInfo = value;
		}
		
	}
}