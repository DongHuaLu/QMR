package com.game.longyuan.message{
	import com.game.longyuan.bean.ShowEffectInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 特效展示
	 */
	public class ResShowEffectToClientMessage extends Message {
	
		//特效信息
		private var _showeffectinfo: ShowEffectInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//特效信息
			writeBean(_showeffectinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//特效信息
			_showeffectinfo = readBean(ShowEffectInfo) as ShowEffectInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115106;
		}
		
		/**
		 * get 特效信息
		 * @return 
		 */
		public function get showeffectinfo(): ShowEffectInfo{
			return _showeffectinfo;
		}
		
		/**
		 * set 特效信息
		 */
		public function set showeffectinfo(value: ShowEffectInfo): void{
			this._showeffectinfo = value;
		}
		
	}
}