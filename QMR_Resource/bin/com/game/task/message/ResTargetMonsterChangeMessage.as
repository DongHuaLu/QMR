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
	 * 讨伐任务怪物状态变化
	 */
	public class ResTargetMonsterChangeMessage extends Message {
	
		//怪物信息
		private var _monsterInfo: TargetMonsterInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物信息
			writeBean(_monsterInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物信息
			_monsterInfo = readBean(TargetMonsterInfo) as TargetMonsterInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120110;
		}
		
		/**
		 * get 怪物信息
		 * @return 
		 */
		public function get monsterInfo(): TargetMonsterInfo{
			return _monsterInfo;
		}
		
		/**
		 * set 怪物信息
		 */
		public function set monsterInfo(value: TargetMonsterInfo): void{
			this._monsterInfo = value;
		}
		
	}
}