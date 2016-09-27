package com.game.map.message{
	import com.game.map.bean.DropGoodsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围掉落物品
	 */
	public class ResRoundGoodsMessage extends Message {
	
		//周围掉落物品信息
		private var _goods: DropGoodsInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围掉落物品信息
			writeBean(_goods);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围掉落物品信息
			_goods = readBean(DropGoodsInfo) as DropGoodsInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101103;
		}
		
		/**
		 * get 周围掉落物品信息
		 * @return 
		 */
		public function get goods(): DropGoodsInfo{
			return _goods;
		}
		
		/**
		 * set 周围掉落物品信息
		 */
		public function set goods(value: DropGoodsInfo): void{
			this._goods = value;
		}
		
	}
}