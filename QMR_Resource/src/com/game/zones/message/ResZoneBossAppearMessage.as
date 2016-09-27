package com.game.zones.message{
	import com.game.zones.bean.ZoneMonstrInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * BOSS出现
	 */
	public class ResZoneBossAppearMessage extends Message {
	
		//副本怪物信息
		private var _zoenmonstrinfo: ZoneMonstrInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本怪物信息
			writeBean(_zoenmonstrinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本怪物信息
			_zoenmonstrinfo = readBean(ZoneMonstrInfo) as ZoneMonstrInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128111;
		}
		
		/**
		 * get 副本怪物信息
		 * @return 
		 */
		public function get zoenmonstrinfo(): ZoneMonstrInfo{
			return _zoenmonstrinfo;
		}
		
		/**
		 * set 副本怪物信息
		 */
		public function set zoenmonstrinfo(value: ZoneMonstrInfo): void{
			this._zoenmonstrinfo = value;
		}
		
	}
}