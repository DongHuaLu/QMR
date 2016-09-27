package com.game.task.message{
	import com.game.task.bean.TargetMonsterInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 需要同步的怪物信息
	 */
	public class ResTargetMonsterMessage extends Message {
	
		//登录时返回的讨伐怪物信息
		private var _monsterInfoList: Vector.<TargetMonsterInfo> = new Vector.<TargetMonsterInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//登录时返回的讨伐怪物信息
			writeShort(_monsterInfoList.length);
			for (i = 0; i < _monsterInfoList.length; i++) {
				writeBean(_monsterInfoList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//登录时返回的讨伐怪物信息
			var monsterInfoList_length : int = readShort();
			for (i = 0; i < monsterInfoList_length; i++) {
				_monsterInfoList[i] = readBean(TargetMonsterInfo) as TargetMonsterInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120113;
		}
		
		/**
		 * get 登录时返回的讨伐怪物信息
		 * @return 
		 */
		public function get monsterInfoList(): Vector.<TargetMonsterInfo>{
			return _monsterInfoList;
		}
		
		/**
		 * set 登录时返回的讨伐怪物信息
		 */
		public function set monsterInfoList(value: Vector.<TargetMonsterInfo>): void{
			this._monsterInfoList = value;
		}
		
	}
}