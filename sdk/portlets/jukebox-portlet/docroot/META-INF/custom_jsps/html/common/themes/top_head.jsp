<%@ include file="/html/common/init.jsp" %>

<script type="text/javascript">
	// <![CDATA[
		var Temporal = {};

		Temporal.getPortalURL = function() {
			return "<%= themeDisplay.getPortalURL() %>";
		}
	// ]]>
</script>

<liferay-util:include page="/html/common/themes/top_head.portal.jsp" />


