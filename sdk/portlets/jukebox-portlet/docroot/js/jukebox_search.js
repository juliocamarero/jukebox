AUI.add(
	'liferay-jukebox-search',
	function(A) {
		var Lang = A.Lang;

		var SearchImpl = A.Component.create (
			{
				AUGMENTS: [A.AutoCompleteBase],

				EXTENDS: A.Base,

				NAME: 'searchimpl',

				prototype: {
					initializer: function() {
						var instance = this;

						this._bindUIACBase();
						this._syncUIACBase();
					}
				}
			}
		);

		var JukeBoxSearch = A.Component.create(
			{
				EXTENDS: SearchImpl,

				NAME: 'jukeboxsearch',

				ATTRS: {
					minQueryLength: {
						validator: Lang.isNumber,
						value: 0
					},

					queryDelay: {
						validator: Lang.isNumber,
						value: 300
					},

					resultFilters: {
						setter: '_setResultFilters',
						value: 'phraseMatch'
					},

					resultTextLocator: {
						setter: '_setLocator',
						value: 'search'
					}
				}
			}
		);

		var JukeBoxContentSearch = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'jukeboxcontentsearch',

				ATTRS: {
					contentPanel: {
						setter: A.one
					},
					inputNode: {
						setter: A.one
					},
					resourceURL: {
						validator: Lang.isString
					}
				},

				prototype: {
					initializer: function(config) {
						var instance = this;

						var contentSearch = new JukeBoxSearch(
							{
								inputNode: instance.get('inputNode')
							}
						);

						instance._contentPanel = instance.get('contentPanel');
						instance._resourceURL = instance.get('resourceURL');

						instance._search = contentSearch;

						instance._bindUISearch();
					},

					_afterSuccess: function(event) {
						var instance = this;

						instance._contentPanel.setContent(event.currentTarget.get('responseData'));
					},

					_bindUISearch: function() {
						var instance = this;

						instance._search.after('query', instance._refreshContentList, instance);

						instance.get('inputNode').on('keydown', instance._onSearchInputKeyDown, instance);
					},

					_onSearchInputKeyDown: function(event) {
						if (event.isKey('ENTER')) {
							event.halt();
						}
					},

					_refreshContentList: function(event) {
						var instance = this;

						A.io.request(
							instance._resourceURL,
							{
								after: {
									success: A.bind('_afterSuccess', instance)
								},
								data: instance.ns(
									{
										keywords: instance.get('inputNode').val()
									}
								)
							}
						);
					}
				}
			}
		);

		Liferay.JukeBoxContentSearch = JukeBoxContentSearch;
		Liferay.JukeBoxSearch = JukeBoxSearch;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-request', 'autocomplete-base', 'autocomplete-filters', 'liferay-portlet-base']
	}
);