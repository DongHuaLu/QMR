package com.game.map.message{
	import com.game.map.bean.EffectInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围效果
	 */
	public class ResRoundEffectMessage extends Message {
	
		//周围效果信息
		private var _Effect: EffectInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围效果信息
			writeBean(_Effect);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围效果信息
			_Effect = readBean(EffectInfo) as EffectInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101131;
		}
		
		/**
		 * get 周围效果信息
		 * @return 
		 */
		public function get Effect(): EffectInfo{
			return _Effect;
		}
		
		/**
		 * set 周围效果信息
		 */
		public function set Effect(value: EffectInfo): void{
			this._Effect = value;
		}
		
	}
}