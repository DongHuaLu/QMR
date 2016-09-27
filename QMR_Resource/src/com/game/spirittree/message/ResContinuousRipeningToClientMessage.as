package com.game.spirittree.message{
	import com.game.spirittree.bean.Rewardbriefinfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 连续催熟返回奖励消息
	 */
	public class ResContinuousRipeningToClientMessage extends Message {
	
		//奖励道具简要信息
		private var _rewardbriefinfo: Vector.<Rewardbriefinfo> = new Vector.<Rewardbriefinfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//奖励道具简要信息
			writeShort(_rewardbriefinfo.length);
			for (i = 0; i < _rewardbriefinfo.length; i++) {
				writeBean(_rewardbriefinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//奖励道具简要信息
			var rewardbriefinfo_length : int = readShort();
			for (i = 0; i < rewardbriefinfo_length; i++) {
				_rewardbriefinfo[i] = readBean(Rewardbriefinfo) as Rewardbriefinfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133110;
		}
		
		/**
		 * get 奖励道具简要信息
		 * @return 
		 */
		public function get rewardbriefinfo(): Vector.<Rewardbriefinfo>{
			return _rewardbriefinfo;
		}
		
		/**
		 * set 奖励道具简要信息
		 */
		public function set rewardbriefinfo(value: Vector.<Rewardbriefinfo>): void{
			this._rewardbriefinfo = value;
		}
		
	}
}