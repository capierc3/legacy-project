### Quality Policy
> Describe your Quality Policy in detail for this Sprint (remember what I ask you to do when I talk about the "In your Project" part in the lectures and what is mentioned after each assignment (in due course you will need to fill out all of them, check which ones are needed for each Deliverable). You should keep adding things to this file and adjusting your policy as you go.

**GitHub Workflow** (due Feb 2nd)
  > Each sprint will have a dedicated branch, pulled from the master branch.  It will be named 'sprintX' where X is the current sprint iteration number.
  > Each user story will have an independent branch pulled from 'sprintX' and be named 'US_X' where X is the user story number generated from Taiga
  > If multiple developers are working on the same user story but different tasks, separate branches shall be generated (1 for each developer) and named 'TASK_X' where TaskName aligns with the task being worked
  > Upon the completion of a user story, the sprintX branch shall be merged into the usX branch and any conflicts shall be resolved.  Upon successful testing, a pull request will be generated to have the changes merged into sprintX branch.
  > At the completion of the sprint, the sprintX branch will be thoroughly tested.  Upon completion of testing, the branch will be merged into master by the appointed Git Master

  > Commit Comments: All commit comments shall start with TASK# and action description of changes. Example: "45 - commenting out bad button"

  > Conflicts: All conflicts are to be handled by the developer responsible for generating the pull request.

  > Code Removal: Before removing code, comment out the lines of code no longer being used, and start with a //TODO comment header, Developer Name, and reason for removal for cleanup in last sprint

  > New classes do need to include @author in Javadoc comments
  > Submit pull request when moving a ticket over to Ready For Test, Tag @everyone in Git channel. Once tested and reviewed, Reviewer will add a comment in PR with feedback or approval.
  > After pull request is approved by the Tester/Reviewer, Approver merges the ticket in, closes the ticket in Taiga


**Unit Tests Blackbox** (due start Sprint 2)
  > Whoever writes the Blackbox tests for the class is NOT the developer that created the class
  > JavaDoc comments need to be extremely clear on parameters passed in, and general functionality to create these tests
  > At least one test class is created and ran if the pull request contains functionality. Must successfully pass all tests and test all equivalence partitions

 **Unit Tests Whitebox** (due Feb 25th)
  > At least one test class is created and ran if the pull request contains functionality. 
  > Code coverage must obtain 60% coverage of new code.
  > Old code will be omitted from this requirement during sprint 2.
  > If the addition is just a UI change, a code review should suffice. 
  > Person who wrote the code will write and perform whitebox testing. 

**Code Review** (due Feb 25th)
  > When code is ready for review, the requester will complete the pull request checklist (listed below) then submit a pull request and attach the completed checklist to the pull request.
  > Developer will mention in slack that a pull request has been submitted and is ready for review. 
  > When reviewer is reviewing, reviewer should walk through reviewer checklist (listed below) and add any additional comments as a comment to the pull request. 
  > If all boxes are checked on checklist and code looks good, then reviewer will approve pull request but will not merge, git master must do the merge.    
  > If checklist is not complete, the reviewer should reject the pull request.
  > Include a checklist/questions list which every developer will need to fill out/answer when creating a Pull Request to the Dev branch. 
  > Include a checklist/question list which every reviewer will need to fill out/answer when conducting a review, this checklist (and the answers of course) need to be put into the Pull Request review.
  >
  >####Developer checklist:
  >- Use intention-revealing variable names
  >- Classes are small and manageable
  >- Methods only do one thing
  >- Code is free of duplication
  >- All Classes and methods contain clear and concise comments and javaDocs
  >- Class variables are immutable
  >- Branch is a fast-forward of the parent branch (merge parent branch into child to verify)
  
   >####Reviewer checklist:
  >- All criteria in Developer checklist has been met
  >- A peer review of the code is performed
  >- Unit tests are written on appropriate methods

**Static Analysis**  (due start Sprint 3)
  > Static analysis should be performed at the very least when a pull request is made that includes any major code changes. It would be more preferable to have static analysis of the code done
  > after a task is complete when new code is written. If Static Analysis is done during a task, note that it was done in the class header to help speed up the process of the pull request and minimize rework of testing.    

**Continuous Integration**  (due start Sprint 3)
  > No code should be pulled into the dev branch that will not build with Travis CI. It is a good idea after a task is performed to make sure the code will build during the "Ready for Test" review of a task.
