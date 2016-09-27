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
	 * 返回前端突破境界信息
	 */
	public class ResBreakthroughToClientMessage extends Message {
	
		//境界信息
		private var _bealmInfo: RealmInfo;
		
		//0失败，1成功
		private var _result: int;
		
		//失败获得经验类型：0普通，1小暴击，2大暴击
		private var _type: int;
		
		//失败获得经验
		private var _exp: int;
		
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
			//失败获得经验类型：0普通，1小暴击，2大暴击
			writeInt(_type);
			//失败获得经验
			writeInt(_exp);
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
			//失败获得经验类型：0普通，1小暴击，2大暴击
			_type = readInt();
			//失败获得经验
			_exp = readInt();
			//提示内容
			_prompt = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 158102;
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
		 * get 失败获得经验类型：0普通，1小暴击，2大暴击
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 失败获得经验类型：0普通，1小暴击，2大暴击
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 失败获得经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 失败获得经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
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