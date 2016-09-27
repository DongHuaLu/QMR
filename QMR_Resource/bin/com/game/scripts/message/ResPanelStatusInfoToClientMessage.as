package com.game.scripts.message{
	import com.game.scripts.bean.PanelStatusInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送前端面板改变状态信息
	 */
	public class ResPanelStatusInfoToClientMessage extends Message {
	
		//面板改变状态信息
		private var _panelStatusInfo: PanelStatusInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板改变状态信息
			writeBean(_panelStatusInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板改变状态信息
			_panelStatusInfo = readBean(PanelStatusInfo) as PanelStatusInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 148103;
		}
		
		/**
		 * get 面板改变状态信息
		 * @return 
		 */
		public function get panelStatusInfo(): PanelStatusInfo{
			return _panelStatusInfo;
		}
		
		/**
		 * set 面板改变状态信息
		 */
		public function set panelStatusInfo(value: PanelStatusInfo): void{
			this._panelStatusInfo = value;
		}
		
	}
}