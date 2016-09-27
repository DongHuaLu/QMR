package com.game.dianjiangchun.message{
	import com.game.dianjiangchun.bean.DianjiangchunInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 点绛唇信息发送到客户端
	 */
	public class SendDianjiangchunInfoToClientMessage extends Message {
	
		//点绛唇保存信息
		private var _stCurInfo: DianjiangchunInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//点绛唇保存信息
			writeBean(_stCurInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//点绛唇保存信息
			_stCurInfo = readBean(DianjiangchunInfo) as DianjiangchunInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 116101;
		}
		
		/**
		 * get 点绛唇保存信息
		 * @return 
		 */
		public function get stCurInfo(): DianjiangchunInfo{
			return _stCurInfo;
		}
		
		/**
		 * set 点绛唇保存信息
		 */
		public function set stCurInfo(value: DianjiangchunInfo): void{
			this._stCurInfo = value;
		}
		
	}
}