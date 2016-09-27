package com.game.stalls.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开摊位列表面板
	 */
	public class ReqStallsOpenUpMessage extends Message {
	
		//索引起点
		private var _indexlittle: int;
		
		//索引终点
		private var _indexLarge: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//索引起点
			writeInt(_indexlittle);
			//索引终点
			writeInt(_indexLarge);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//索引起点
			_indexlittle = readInt();
			//索引终点
			_indexLarge = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123201;
		}
		
		/**
		 * get 索引起点
		 * @return 
		 */
		public function get indexlittle(): int{
			return _indexlittle;
		}
		
		/**
		 * set 索引起点
		 */
		public function set indexlittle(value: int): void{
			this._indexlittle = value;
		}
		
		/**
		 * get 索引终点
		 * @return 
		 */
		public function get indexLarge(): int{
			return _indexLarge;
		}
		
		/**
		 * set 索引终点
		 */
		public function set indexLarge(value: int): void{
			this._indexLarge = value;
		}
		
	}
}