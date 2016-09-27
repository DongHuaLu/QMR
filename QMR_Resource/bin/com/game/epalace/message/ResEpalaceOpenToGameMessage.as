package com.game.epalace.message{
	import com.game.player.bean.PlayerAppearanceInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开面板返回消息
	 */
	public class ResEpalaceOpenToGameMessage extends Message {
	
		//恢复次数冷却时间
		private var _time: int;
		
		//任务ID
		private var _task: int;
		
		//当前站立位置
		private var _pos: int;
		
		//已经移动次数
		private var _movenum: int;
		
		//造型信息
		private var _appearanceInfo: com.game.player.bean.PlayerAppearanceInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//恢复次数冷却时间
			writeInt(_time);
			//任务ID
			writeInt(_task);
			//当前站立位置
			writeByte(_pos);
			//已经移动次数
			writeByte(_movenum);
			//造型信息
			writeBean(_appearanceInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//恢复次数冷却时间
			_time = readInt();
			//任务ID
			_task = readInt();
			//当前站立位置
			_pos = readByte();
			//已经移动次数
			_movenum = readByte();
			//造型信息
			_appearanceInfo = readBean(com.game.player.bean.PlayerAppearanceInfo) as com.game.player.bean.PlayerAppearanceInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143103;
		}
		
		/**
		 * get 恢复次数冷却时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 恢复次数冷却时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get task(): int{
			return _task;
		}
		
		/**
		 * set 任务ID
		 */
		public function set task(value: int): void{
			this._task = value;
		}
		
		/**
		 * get 当前站立位置
		 * @return 
		 */
		public function get pos(): int{
			return _pos;
		}
		
		/**
		 * set 当前站立位置
		 */
		public function set pos(value: int): void{
			this._pos = value;
		}
		
		/**
		 * get 已经移动次数
		 * @return 
		 */
		public function get movenum(): int{
			return _movenum;
		}
		
		/**
		 * set 已经移动次数
		 */
		public function set movenum(value: int): void{
			this._movenum = value;
		}
		
		/**
		 * get 造型信息
		 * @return 
		 */
		public function get appearanceInfo(): com.game.player.bean.PlayerAppearanceInfo{
			return _appearanceInfo;
		}
		
		/**
		 * set 造型信息
		 */
		public function set appearanceInfo(value: com.game.player.bean.PlayerAppearanceInfo): void{
			this._appearanceInfo = value;
		}
		
	}
}