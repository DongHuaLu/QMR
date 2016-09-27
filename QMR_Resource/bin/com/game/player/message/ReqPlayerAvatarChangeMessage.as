package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求玩家头像变更
	 */
	public class ReqPlayerAvatarChangeMessage extends Message {
	
		//新头像模板ID
		private var _avatarid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//新头像模板ID
			writeInt(_avatarid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//新头像模板ID
			_avatarid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103209;
		}
		
		/**
		 * get 新头像模板ID
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 新头像模板ID
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
	}
}