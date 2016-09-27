package com.game.longyuan.message{
	import com.game.longyuan.bean.LongYuanInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开龙元心法面板
	 */
	public class ResLongYuanOpenMessage extends Message {
	
		//真气值
		private var _zhenqi: int;
		
		//龙元心法信息
		private var _longyuaninfo: LongYuanInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//真气值
			writeInt(_zhenqi);
			//龙元心法信息
			writeBean(_longyuaninfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//真气值
			_zhenqi = readInt();
			//龙元心法信息
			_longyuaninfo = readBean(LongYuanInfo) as LongYuanInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115101;
		}
		
		/**
		 * get 真气值
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 真气值
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 龙元心法信息
		 * @return 
		 */
		public function get longyuaninfo(): LongYuanInfo{
			return _longyuaninfo;
		}
		
		/**
		 * set 龙元心法信息
		 */
		public function set longyuaninfo(value: LongYuanInfo): void{
			this._longyuaninfo = value;
		}
		
	}
}