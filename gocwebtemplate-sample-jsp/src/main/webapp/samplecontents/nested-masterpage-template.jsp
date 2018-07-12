<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="row">
    <!-- left column-->
    <section class="col-md-8">
        <tiles:insertAttribute name="leftcontent" />
    </section>
    <!-- right column-->
    <aside class="col-md-4 mrgn-tp-lg">
        <!-- include weather widget here so it is displayed on all pages-->
        <div class="well">
            <section class="wb-feeds limit-1">
             <h3>Ottawa Weather</h3>
             <ul class="feeds-cont">
              <li>
               <a href="http://weather.gc.ca/rss/city/on-118_e.xml" rel="external">Ottawa Ontario</a>
              </li>
             </ul>
            </section>
            <!-- include a content place holder should the page require to add content to the right column below the weather widget-->
            <tiles:insertAttribute name="rightcontent" />
        </div>
    </aside>
</div>