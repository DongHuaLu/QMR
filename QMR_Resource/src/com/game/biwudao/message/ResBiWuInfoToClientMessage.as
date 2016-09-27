package com.game.biwudao.message{
	import com.game.biwudao.bean.BiWuInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送比武岛信息
	 */
	public class ResBiWuInfoToClientMessage extends Message {
	
		//比武岛信息
		private var _biWuInfo: BiWuInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//比武岛信息
			writeBean(_biWuInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//比武岛信息
			_biWuInfo = readBean(BiWuInfo) as BiWuInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157101;
		}
		
		/**
		 * get 比武岛信息
		 * @return 
		 */
		public function get biWuInfo(): BiWuInfo{
			return _biWuInfo;
		}
		
		/**
		 * set 比武岛信息
		 */
		public function set biWuInfo(value: BiWuInfo): void{
			this._biWuInfo = value;
		}
		
	}
}