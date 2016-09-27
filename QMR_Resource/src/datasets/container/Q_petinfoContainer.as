package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_petinfo;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_petinfo
	 */
	public class Q_petinfoContainer {
		
		private var _list:Vector.<Q_petinfo> = new Vector.<Q_petinfo>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_petinfoContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_petinfo = new Q_petinfo();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_model_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_petinfo>{
			return _list;
		}
		
		public function get dict(): Dictionary{
			return _dict;
		}
		
		public function get version(): int{
			return _version;
		}
	}
}
