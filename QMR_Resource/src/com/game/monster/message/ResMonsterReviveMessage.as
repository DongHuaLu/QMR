package com.game.monster.message{
	import com.game.map.bean.MonsterInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 怪物复活
	 */
	public class ResMonsterReviveMessage extends Message {
	
		//怪物信息
		private var _monster: com.game.map.bean.MonsterInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物信息
			writeBean(_monster);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物信息
			_monster = readBean(com.game.map.bean.MonsterInfo) as com.game.map.bean.MonsterInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114109;
		}
		
		/**
		 * get 怪物信息
		 * @return 
		 */
		public function get monster(): com.game.map.bean.MonsterInfo{
			return _monster;
		}
		
		/**
		 * set 怪物信息
		 */
		public function set monster(value: com.game.map.bean.MonsterInfo): void{
			this._monster = value;
		}
		
	}
}