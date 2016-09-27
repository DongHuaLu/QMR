package com.game.realm.message{
	import com.game.realm.bean.RealmInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回前端强化境界信息
	 */
	public class ResIntensifyToClientMessage extends Message {
	
		//境界信息
		private var _bealmInfo: RealmInfo;
		
		//0失败，1成功
		private var _result: int;
		
		//提示内容
		private var _prompt: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//境界信息
			writeBean(_bealmInfo);
			//0失败，1成功
			writeInt(_result);
			//提示内容
			writeString(_prompt);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//境界信息
			_bealmInfo = readBean(RealmInfo) as RealmInfo;
			//0失败，1成功
			_result = readInt();
			//提示内容
			_prompt = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 158103;
		}
		
		/**
		 * get 境界信息
		 * @return 
		 */
		public function get bealmInfo(): RealmInfo{
			return _bealmInfo;
		}
		
		/**
		 * set 境界信息
		 */
		public function set bealmInfo(value: RealmInfo): void{
			this._bealmInfo = value;
		}
		
		/**
		 * get 0失败，1成功
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 0失败，1成功
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
		/**
		 * get 提示内容
		 * @return 
		 */
		public function get prompt(): String{
			return _prompt;
		}
		
		/**
		 * set 提示内容
		 */
		public function set prompt(value: String): void{
			this._prompt = value;
		}
		
	}
}