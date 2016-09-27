package com.game.rank.message{
	import com.game.rank.bean.Rankinfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔信息发送到客户端
	 */
	public class ResRankinfoToClientMessage extends Message {
	
		//军衔保存信息
		private var _rankinfo: Rankinfo;
		
		//今日得到的军功值
		private var _dayrankexp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//军衔保存信息
			writeBean(_rankinfo);
			//今日得到的军功值
			writeInt(_dayrankexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//军衔保存信息
			_rankinfo = readBean(Rankinfo) as Rankinfo;
			//今日得到的军功值
			_dayrankexp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 117101;
		}
		
		/**
		 * get 军衔保存信息
		 * @return 
		 */
		public function get rankinfo(): Rankinfo{
			return _rankinfo;
		}
		
		/**
		 * set 军衔保存信息
		 */
		public function set rankinfo(value: Rankinfo): void{
			this._rankinfo = value;
		}
		
		/**
		 * get 今日得到的军功值
		 * @return 
		 */
		public function get dayrankexp(): int{
			return _dayrankexp;
		}
		
		/**
		 * set 今日得到的军功值
		 */
		public function set dayrankexp(value: int): void{
			this._dayrankexp = value;
		}
		
	}
}