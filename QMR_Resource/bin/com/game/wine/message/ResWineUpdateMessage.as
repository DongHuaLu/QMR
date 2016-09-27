package com.game.wine.message{
	import com.game.wine.bean.WineUpdate;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 变动信息
	 */
	public class ResWineUpdateMessage extends Message {
	
		//变动信息
		private var _update: WineUpdate;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//变动信息
			writeBean(_update);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//变动信息
			_update = readBean(WineUpdate) as WineUpdate;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 159102;
		}
		
		/**
		 * get 变动信息
		 * @return 
		 */
		public function get update(): WineUpdate{
			return _update;
		}
		
		/**
		 * set 变动信息
		 */
		public function set update(value: WineUpdate): void{
			this._update = value;
		}
		
	}
}