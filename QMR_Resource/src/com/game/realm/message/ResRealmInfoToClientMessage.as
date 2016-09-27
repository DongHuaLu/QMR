package com.game.realm.message{
	import com.game.realm.bean.RealmInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 境界信息
	 */
	public class ResRealmInfoToClientMessage extends Message {
	
		//境界信息
		private var _bealmInfo: RealmInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//境界信息
			writeBean(_bealmInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//境界信息
			_bealmInfo = readBean(RealmInfo) as RealmInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 158101;
		}
		
		/**
		 * get 境界信息
		 * @return 
		 */
		public function get bealmInfo(): RealmInfo{
			return _bealmInfo;
		}
		
		/**
		 * set 境界信息
		 */
		public function set bealmInfo(value: RealmInfo): void{
			this._bealmInfo = value;
		}
		
	}
}