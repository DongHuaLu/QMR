package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_task_main;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_main
	 */
	public class Q_task_mainContainer {
		
		private var _list:Vector.<Q_task_main> = new Vector.<Q_task_main>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_task_mainContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_task_main = new Q_task_main();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_taskid)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_task_main>{
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
