/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { TshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TActivityDetailComponent } from '../../../../../../main/webapp/app/entities/t-activity/t-activity-detail.component';
import { TActivityService } from '../../../../../../main/webapp/app/entities/t-activity/t-activity.service';
import { TActivity } from '../../../../../../main/webapp/app/entities/t-activity/t-activity.model';

describe('Component Tests', () => {

    describe('TActivity Management Detail Component', () => {
        let comp: TActivityDetailComponent;
        let fixture: ComponentFixture<TActivityDetailComponent>;
        let service: TActivityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [TshopTestModule],
                declarations: [TActivityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TActivityService,
                    JhiEventManager
                ]
            }).overrideTemplate(TActivityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TActivityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TActivityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TActivity('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tActivity).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
