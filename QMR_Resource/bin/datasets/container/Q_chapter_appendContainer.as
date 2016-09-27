package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_chapter_append;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_chapter_append
	 */
	public class Q_chapter_appendContainer {
		
		private var _list:Vector.<Q_chapter_append> = new Vector.<Q_chapter_append>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_chapter_appendContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_chapter_append = new Q_chapter_append();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_chapter)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_chapter_append>{
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
