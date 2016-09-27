package com.game.chestbox.message{
	import com.game.chestbox.bean.ChestBoxInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱盒子信息发送到客户端
	 */
	public class ResChestBoxInfoToClientMessage extends Message {
	
		//宝箱盒子信息
		private var _chestboxinfo: ChestBoxInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宝箱盒子信息
			writeBean(_chestboxinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝箱盒子信息
			_chestboxinfo = readBean(ChestBoxInfo) as ChestBoxInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 156101;
		}
		
		/**
		 * get 宝箱盒子信息
		 * @return 
		 */
		public function get chestboxinfo(): ChestBoxInfo{
			return _chestboxinfo;
		}
		
		/**
		 * set 宝箱盒子信息
		 */
		public function set chestboxinfo(value: ChestBoxInfo): void{
			this._chestboxinfo = value;
		}
		
	}
}